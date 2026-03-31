# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Repository Structure

This is a monorepo with:
- `legacy/` — original Google App Engine Java/JSP application (see below)
- `gadoboi-api/` — new Spring Boot API (hexagonal architecture)
- `gadoboi-front/` — new Angular frontend (Tailwind CSS + shadcn-ng)
- `refs/` — design screenshots and HTML mockups for the new frontend
- `docs/PRD.md` — migration PRD

## Legacy Project (legacy/)

The original app is a **Google App Engine Java 6/7 web app** for **cattle (livestock) management and feed ratio calculation** in Brazil.

### Running the legacy project

It requires the App Engine SDK and Eclipse tooling (originally developed with Eclipse). There are no build scripts — it was launched from Eclipse with the App Engine dev server plugin.

### Architecture

```
src/br/com/gadoboi/
  bean/       # JPA entities + pure calculation classes
  servlet/    # HttpServlets acting as controllers (one per entity)
  dao/EMF.java  # EntityManagerFactory singleton
war/
  *.jsp        # ~45 JSP view pages
  WEB-INF/web.xml
  xml/estados.xml, cidades.xml  # Brazilian states/cities reference data
```

### Domain Model

**Core entities (Google Datastore via JPA/DataNucleus):**
- `Cliente` — user/client with address (`Endereco`), login, personal data
- `Gado` — cattle breed, owned by a `Cliente`
- `Insumo` — feed ingredient, owned by a `Cliente`
- `Racao` — feed formula: links `Gado`, `Regiao`, `Manejo`, and `Cliente`; has multiple `RacaoInsumo` (ingredient+quantity) and `Metodo` (method) children
- `VendaRacao` — nearly identical to `Racao` but tagged for commercial sale
- `Manejo` — management type (confinement, semi-confinement, etc.)
- `Regiao` — geographic region
- `Metodo` — method applied within a formula
- `Rendimento` — profitability calculation result (not a master entity)

**Multi-tenancy:** every entity has a `cliente` FK; all queries filter by current user.

### Business Logic (bean layer)

Feed formula hierarchy (all classes in `bean/`):
```
RacaoDemonstracao (abstract, base ingredients: corn, soybean, urea, ammonium sulfate, mineral mix)
  └─ Confinamento (abstract, adds: ionophore, calcitic lime)
       ├─ ConfinamentoOne  — formula A percentages
       └─ ConfinamentoTwo  — formula B percentages
  └─ SemiConfinamento (abstract)
       ├─ SemiConfinamentoChuva  — rainy season
       └─ SemiConfinamentoEstiagem  — dry season
  └─ Proteinato (abstract, adds: white salt)
       ├─ ProteinatoChuva
       └─ ProteinatoEstiagem
```

`Rendimento.calcular()` computes livestock profitability:
- stocking rate, daily concentrate/head, total concentrate, daily cost/animal, expected weight gain, total cost/gain, net result

### Persistence Pattern

All servlets use this pattern with `EMF.getEMF()`:
```java
EntityManager em = EMF.get().createEntityManager();
// find by Key: em.find(Entity.class, KeyFactory.stringToKey(id))
// query: em.createQuery("SELECT o FROM Racao AS o WHERE o.cliente = :c").setParameter("c", cliente)
em.getTransaction().begin(); em.persist(obj); em.getTransaction().commit();
```

Keys are `com.google.appengine.api.datastore.Key` (not integer/UUID). When creating new records, code uses `Math.random() * 100000` to generate a numeric key.

Dynamic form handling in `ServletRacao`/`ServletVendaRacao`: iterates up to 60 parameter slots (`idInsumo1`…`idInsumo60`, `metodo1`…`metodo60`).

### Known issues in legacy code
- No server-side validation; client-side only via jQuery masks
- Password field populated in form but never persisted in `ServletCliente`
- Hardcoded contact email: `silvio_yousseff@hotmail.com`
- jQuery 1.6.1 (2011)

---

## New Projects

### gadoboi-api (Spring Boot)

**Stack:** Spring Boot 3.5, Java 21, Maven, PostgreSQL 16+, Flyway, Spring Security + JWT (jjwt 0.12.x)

**Commands:**
```bash
cd gadoboi-api
./mvnw spring-boot:run          # run dev (requires PostgreSQL on localhost:5432/gadoboi)
./mvnw test                     # run tests
./mvnw package -DskipTests      # build JAR
```

**Hexagonal package layout:**
```
br.com.gadoboi.api/
  domain/model/          # Java records (immutable domain objects)
  domain/port/in/        # Use case interfaces (GadoUseCase, RacaoUseCase, ...)
  domain/port/out/       # Repository interfaces (GadoRepository, ...)
  application/service/   # Use case implementations
  infrastructure/
    adapter/in/rest/     # @RestController + DTOs
    adapter/out/persistence/  # JPA @Entity, Spring Data repos, mappers
    config/              # Security, CORS
  db/migration/          # Flyway SQL files (V1__init_schema.sql, ...)
```

Key migration mappings:
- Google Datastore entities → relational tables (`V1__init_schema.sql`)
- `VendaRacao` unified into `racao` table with `status='V'`
- Multi-tenancy: extract `clienteId` from JWT, never trust client-supplied value

### gadoboi-front (Angular)

**Stack:** Angular 21, Tailwind CSS v4, [shadcn-ng](https://ui.adrianub.dev/docs/)

**Commands:**
```bash
cd gadoboi-front
npm start           # dev server on localhost:4200
npm run build       # production build
npm test            # unit tests
```

**App structure:**
```
src/app/
  core/models/       # TypeScript interfaces (Cliente, Gado, Racao, Rendimento, ...)
  core/services/     # ApiService base, per-entity services, AuthStore (Signals)
  core/interceptors/ # authInterceptor adds Bearer token to all requests
  core/guards/       # authGuard redirects to /auth/login
  features/          # Lazy-loaded standalone components per route
```

**API base URL:** `environment.apiUrl` (default `http://localhost:8080`)

Design references live in `refs/` (screenshots + HTML mockups from Stitch).
