# PRD — Migração GadoBoi

**Versão:** 1.0
**Data:** 2026-03-26
**Status:** Em execução

---

## 1. Contexto e Motivação

O GadoBoi é um sistema de **gerenciamento pecuário e cálculo de rações nutritivas** voltado ao mercado brasileiro. O sistema legado foi construído sobre Google App Engine (Java 6/7), JSP, jQuery 1.6 e Google Cloud Datastore (NoSQL), e encontra-se sem manutenção ativa.

### Problemas do legado
- Stack totalmente defasada (Java 6, JSP, jQuery 1.6, GAE SDK deprecado)
- Sem autenticação segura (GAE UserService, senha não persistida)
- Sem validação server-side
- Google Datastore como banco: dificulta relatórios, joins e migração de dados
- Nenhuma API — acoplamento total entre back e front via JSP
- Sem testes automatizados
- Sem CI/CD

### Objetivo
Reescrever o sistema em uma stack moderna, preservando toda a lógica de negócio, e entregá-lo como dois projetos desacoplados: uma API REST e uma SPA Angular.

---

## 2. Escopo da Migração

### Fora do escopo (v1)
- Migração de dados produção do Datastore → PostgreSQL
- Funcionalidade de envio de e-mail de contato (pode ser adicionada como feature posterior)
- Módulo de administração (adm.jsp)

### Dentro do escopo (v1)
Todas as funcionalidades existentes no legado:
- Seeds para dados iniciais
- Cadastro e autenticação de clientes
- CRUD de Gado, Insumo, Manejo, Região
- Criação e gestão de Rações (com ingredientes dinâmicos e métodos)
- Rações para Venda
- Calculadora de Rendimento (lucratividade)
- Fórmulas demonstrativas (Confinamento 1/2, Semi-confinamento chuva/estiagem, Proteinato chuva/estiagem)

---

## 3. Arquitetura Alvo

### Monorepo
```
gadoboi/
├── legacy/            # App GAE original (preservado, não modificar)
├── gadoboi-api/       # API REST Spring Boot
├── gadoboi-front/     # SPA Angular
├── refs/              # Screenshots e HTMLs de referência visual (Stitch)
└── docs/              # PRD, ADRs e documentação
```

### gadoboi-api

**Stack:** Spring Boot 3.5, Java 21, Maven
**Banco:** PostgreSQL 16+ com Flyway para migrations
**Arquitetura:** Hexagonal (Ports & Adapters)
**Auth:** JWT (jjwt 0.12.x) + Spring Security

```
br.com.gadoboi.api/
  domain/
    model/         # Records imutáveis (Cliente, Gado, Insumo, Racao, ...)
    port/
      in/          # Interfaces de caso de uso (GadoUseCase, RacaoUseCase, ...)
      out/         # Interfaces de repositório (GadoRepository, ...)
  application/
    service/       # Implementações dos use cases
  infrastructure/
    adapter/
      in/rest/     # Controllers REST + DTOs de request/response
      out/persistence/
        entity/    # JPA @Entity classes
        repository/ # Spring Data JPA + implementações dos ports out
        mapper/    # Domain model ↔ JPA entity
    config/        # Spring Security, CORS, etc.
  db/migration/    # SQL Flyway (V1__init_schema.sql, ...)
```

**Endpoints base (v1):**
| Método | Path | Descrição |
|--------|------|-----------|
| POST | /api/v1/auth/login | Autenticação |
| POST | /api/v1/auth/cadastro | Registro de cliente |
| GET/POST/PUT/DELETE | /api/v1/gado | CRUD raças |
| GET/POST/PUT/DELETE | /api/v1/insumo | CRUD ingredientes |
| GET/POST/PUT/DELETE | /api/v1/racao | CRUD rações |
| GET/POST/PUT/DELETE | /api/v1/manejo | CRUD tipos de manejo |
| GET/POST/PUT/DELETE | /api/v1/regiao | CRUD regiões |
| POST | /api/v1/rendimento/calcular | Cálculo de lucratividade |
| GET | /api/v1/formulas/{tipo} | Fórmulas demonstrativas |

### gadoboi-front

**Stack:** Angular 21, Tailwind CSS v4, shadcn-ng (https://ui.adrianub.dev/docs/)
**Auth:** JWT armazenado em localStorage via `AuthStore` (Signal-based)

```
src/app/
  core/
    models/        # Interfaces TypeScript espelhando domínio da API
    services/      # ApiService base, serviços por entidade, AuthStore
    interceptors/  # authInterceptor (Bearer token)
    guards/        # authGuard
  shared/
    components/    # Componentes reutilizáveis (layout, sidebar, breadcrumb)
    pipes/
  features/        # Um diretório por tela/feature (lazy-loaded standalone components)
    auth/login
    auth/cadastro
    dashboard
    gado
    insumo
    racao
    manejo
    regiao
    rendimento
    venda-racao
    formulas
```

---

## 4. Mapeamento de Entidades: Datastore → PostgreSQL

| Entidade Legacy (Datastore Key) | Tabela PostgreSQL | Mudanças |
|---|---|---|
| `Cliente` | `cliente` | Key → `BIGSERIAL id`; adicionar `senha_hash` |
| `Endereco` | `endereco` | Key → `BIGSERIAL id` |
| `Gado` | `gado` | Key → `BIGSERIAL id` |
| `Insumo` | `insumo` | Key → `BIGSERIAL id` |
| `Manejo` | `manejo` | Key → `BIGSERIAL id` |
| `Regiao` | `regiao` | Key → `BIGSERIAL id` |
| `Racao` + `VendaRacao` | `racao` | Unificar em uma tabela; `status='V'` para venda |
| `RacaoInsumo` | `racao_insumo` | Junction table com quantidade |
| `Metodo` | `metodo` | FK para `racao` |
| `Rendimento` | `rendimento` | Campos snake_case + `calculado_em TIMESTAMP` |

**Nota:** `VendaRacao` é funcionalmente idêntica a `Racao` com `status = 'V'`. Unificar elimina código duplicado.

---

## 5. Lógica de Negócio Crítica a Preservar

### 5.1 Fórmulas Demonstrativas
Seis fórmulas com percentuais fixos de ingredientes:

| Fórmula | Milho | Farelo Soja | Ureia | Sulfato | Mineral | Ionóforo | Cal | Sal |
|---|---|---|---|---|---|---|---|---|
| Confinamento 1 | 78.34% | 16.0% | 2.04% | 0.36% | 1.20% | 0.06% | 2.0% | — |
| Confinamento 2 | 75.46% | 20.0% | 1.87% | 0.33% | 1.0% | 0.04% | 1.30% | — |
| Semi-conf Chuva | 73.95% | 20.80% | 1.70% | 0.30% | 2.0% | 0.05% | 1.20% | — |
| Semi-conf Estiagem | 70.86% | 24.9% | 1.28% | 0.22% | 1.50% | 0.04% | 1.20% | — |
| Proteinato Chuva | 32.0% | 25.0% | 6.8% | 1.2% | 15.0% | — | — | 20.0% |
| Proteinato Estiagem | 22.0% | 28.0% | 12.8% | 2.2% | 15.0% | — | — | 20.0% |

### 5.2 Cálculo de Rendimento
```
taxaLotacao            = qtdAnimais / tamanhoDaPastagem
qtdConcentradoDiario   = pesoInicial × 0.01
qtdConcentradoTotal    = qtdConcentradoDiario × qtdAnimais × periodoDeTratamento
custoTotal             = precoPorQuiloCon × qtdConcentradoTotal
precoConcentradoDia    = precoPorQuiloCon × qtdConcentradoDiario
ganhoPorCabecaDia      = ganhoDePesoEsperado × rendimentoCarcaca × precoArroba × (1/15)
ganhoTotal             = ganhoDePesoEsperado × qtdAnimais × periodo × rendimentoCarcaca × precoArroba
resultado (lucro)      = ganhoTotal - custoTotal
```

### 5.3 Multi-tenancy
Todas as entidades de negócio têm FK para `cliente`. A API deve filtrar sempre por `clienteId` extraído do JWT — nunca confiar no `clienteId` enviado pelo cliente nos requests de leitura/escrita.

---

## 6. Telas a Implementar (gadoboi-front)

Referências visuais disponíveis em `refs/` (screenshots do Stitch).

| Rota | Componente | Fonte Legacy |
|---|---|---|
| `/auth/login` | LoginComponent | index.html + GAE auth |
| `/auth/cadastro` | CadastroComponent | cadastro.jsp |
| `/dashboard` | DashboardComponent | menu.jsp |
| `/gado` | GadoComponent | gado.jsp + visualizagado.jsp |
| `/insumo` | InsumoComponent | insumo.jsp + visualizainsumo.jsp |
| `/racao` | RacaoComponent | cadastroracao.jsp + visualizaracao.jsp |
| `/racao/venda` | VendaRacaoComponent | vendaracao.jsp |
| `/manejo` | ManejoComponent | manejo.jsp + visualizamanejo.jsp |
| `/regiao` | RegiaoComponent | regiao.jsp + visualizaregiao.jsp |
| `/rendimento` | RendimentoComponent | rendimento.jsp + formrendimento.jsp |
| `/formulas` | FormulasComponent | confinamentoone.jsp + semichuva.jsp + etc. |

**Padrão de UX por CRUD:**
- Listagem com tabela + botões editar/remover
- Form modal ou inline para criar/editar
- Feedback de erro e sucesso via toast (shadcn-ng)
- Formulário de Ração com linhas dinâmicas de ingredientes (substituindo o padrão de até 60 parâmetros do legado)

---

## 7. Fases de Execução

### Fase 1 — Fundação (atual)
- [x] Mover legado para `legacy/`
- [x] Inicializar `gadoboi-api` com estrutura hexagonal e schema SQL
- [x] Inicializar `gadoboi-front` com Angular 21, Tailwind v4, shadcn-ng
- [x] Criar este PRD

### Fase 2 — API Core
- [ ] Implementar JPA entities + adapters de persistência
- [ ] Implementar autenticação JWT (registro, login, refresh)
- [ ] Implementar todos os use cases: Gado, Insumo, Manejo, Regiao
- [ ] Implementar use case Racao (com RacaoInsumo e Metodo em uma transação)
- [ ] Implementar endpoint de Rendimento
- [ ] Implementar endpoints de Fórmulas demonstrativas (somente leitura, dados fixos)
- [ ] Testes de integração com Testcontainers + PostgreSQL

### Fase 3 — Frontend Core
- [ ] Layout shell (sidebar, header, breadcrumb) com shadcn-ng
- [ ] Telas de auth (login, cadastro) com validação reativa
- [ ] Dashboard com cards de resumo
- [ ] CRUDs simples: Gado, Insumo, Manejo, Região
- [ ] CRUD de Ração com form dinâmico de ingredientes
- [ ] Calculadora de Rendimento com exibição de resultados
- [ ] Visualizador de Fórmulas Demonstrativas

### Fase 4 — Qualidade e Deploy
- [ ] Dockerizar gadoboi-api (Dockerfile + docker-compose com PostgreSQL)
- [ ] Dockerizar gadoboi-front (nginx)
- [ ] CI/CD básico (GitHub Actions: build + test)
- [ ] Variáveis de ambiente e configuração de produção

---

## 8. Decisões Técnicas (ADRs)

### ADR-001: Unificar Racao e VendaRacao
**Decisão:** Uma única tabela `racao` com coluna `status` (`'V'` = venda, `null` = interno).
**Motivo:** As duas entidades no legado são estruturalmente idênticas. Ter duas tabelas era duplicação de código sem ganho real.

### ADR-002: Records Java para Domain Model
**Decisão:** Usar `record` Java 21 para as classes de domínio.
**Motivo:** Imutabilidade por padrão, equals/hashCode/toString automáticos, menor boilerplate. JPA entities separadas na camada de infraestrutura.

### ADR-003: Signals para estado no Angular
**Decisão:** Usar Angular Signals (`signal`, `computed`) para gerenciamento de estado local e AuthStore.
**Motivo:** Padrão moderno do Angular 17+, sem dependência de bibliotecas externas (NgRx desnecessário para este porte).

### ADR-004: Tailwind v4 com `@use`
**Decisão:** Tailwind CSS v4 instalado como plugin Sass via `@use "tailwindcss"`.
**Motivo:** v4 é a versão atual, sem necessidade de `tailwind.config.js`.

### ADR-005: Flyway para schema
**Decisão:** Flyway para versionamento do schema SQL.
**Motivo:** `ddl-auto: validate` em produção — Hibernate nunca altera o schema, garantindo migrações controladas.
OBS: Nesse primeiro momento vamos utilizar H2 como banco de dados embeded para facilitar o desenvolvimento
