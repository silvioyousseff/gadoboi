# Sistema de Design: Excelência Editorial & Profundidade Tonal

## 1. Visão Geral e Estrela do Norte Criativa
Este sistema de design foi concebido para transcender a estética comum de SaaS "baseada em templates". Nossa Estrela do Norte é o **"Painel Editorial de Alta Precisão"**. Em vez de uma interface puramente utilitária, buscamos uma experiência que pareça uma publicação digital de luxo: informativa, mas visualmente repousante.

O sistema rompe com a rigidez tradicional através de:
- **Assimetria Intencional:** Layouts que utilizam espaços negativos generosos para guiar o olhar.
- **Camadas Atmosféricas:** Uso de profundidade tonal em vez de linhas demarcadoras.
- **Tipografia de Impacto:** Um contraste nítido entre fontes geométricas modernas para títulos e sans-serifs humanistas para dados densos.

O objetivo é criar um ambiente onde a densidade de informações (tabelas e gráficos) coexista com uma sensação de leveza e "respiro" visual.

---

## 2. Paleta de Cores e Hierarquia de Superfícies
A cor não é apenas estética; é funcional. Utilizamos uma paleta baseada em tons de terra suaves e acentos vibrantes para ação.

### Tonalidades Principais
- **Primary (#9f4200):** O tom "Terracota" exclusivo para ações principais (CTAs). Use `primary_container` (#f47121) para estados de hover.
- **Secondary (#005cba):** Um azul técnico para indicadores de status e links.
- **Tertiary (#1b6d24):** Verde botânico para indicadores de sucesso e crescimento.

### Regra do "Zero Linhas" (The No-Line Rule)
**Proibimos estritamente o uso de bordas sólidas de 1px para separar seções.** A separação deve ser feita exclusivamente através de:
1. **Mudanças de Tom:** Um card `surface_container_lowest` (#ffffff) sobre um fundo `surface_container_low` (#f2f4f7).
2. **Nesting (Aninhamento):** Camadas sobrepostas criam hierarquia natural sem poluição visual.

### Texturas e Gradientes de Assinatura
Para CTAs principais, utilize um gradiente sutil de `primary` para `primary_container`. Isso adiciona "alma" ao componente, evitando o aspecto plano e genérico. Elementos flutuantes (modais/menus) devem utilizar **Glassmorphism**: fundo `surface` com 80% de opacidade e `backdrop-blur` de 12px.

---

## 3. Tipografia
A tipografia é o alicerce da nossa autoridade visual.

- **Display & Headlines (Plus Jakarta Sans):** Uma fonte com personalidade geométrica. Use para grandes números, títulos de seções e dashboards. Transmite modernidade e precisão.
- **Body & Labels (Inter):** A escolha definitiva para legibilidade. Usada para todos os dados, tabelas e textos corridos.

| Escala | Token | Peso | Uso |
| :--- | :--- | :--- | :--- |
| **Display LG** | `display-lg` | 700 | Números de impacto e heros |
| **Headline SM** | `headline-sm` | 600 | Títulos de Cards e Seções |
| **Title MD** | `title-md` | 500 | Subtítulos e labels de grupos |
| **Body MD** | `body-md` | 400 | Texto principal e dados de tabelas |
| **Label SM** | `label-sm` | 600 | Tags, micro-copy e metadados |

---

## 4. Elevação e Profundidade Tonal
A profundidade neste sistema é percebida, não apenas vista. Abandonamos as sombras pesadas em favor do **Layering Princípio**.

- **Camadas de Superfície:**
  - Nível 0 (Fundo): `surface` (#f7f9fc)
  - Nível 1 (Seções): `surface_container_low` (#f2f4f7)
  - Nível 2 (Cards): `surface_container_lowest` (#ffffff)
  
- **Sombras Ambiente:** Quando o componente precisa "flutuar" (ex: menus suspensos), use sombras extra-difusas.
  - *Configuração:* `0px 12px 32px rgba(25, 28, 30, 0.06)`. Note que a cor da sombra é uma versão tingida de `on_surface`, nunca preto puro.
  
- **Ghost Borders:** Se uma borda for indispensável para acessibilidade, utilize o token `outline_variant` com **20% de opacidade**. Nunca 100%.

---

## 5. Componentes Principais

### Sidebar (Navegação Minimalista)
- **Visual:** Fundo `surface_container_lowest`. Sem linha divisória à direita.
- **Ícones:** Linhas finas, centralizados, com estado ativo usando `primary`.
- **Interação:** O item ativo não deve ter um "box" pesado, mas sim um indicador lateral sutil ou uma mudança tonal suave.

### Cards e Listas
- **Regra de Ouro:** Proibido o uso de linhas divisórias entre itens de lista. Use o espaçamento `spacing.4` (1rem) ou alternância sutil entre `surface` e `surface_container_low`.
- **Arredondamento:** Siga a escala `md` (0.75rem) para cards e `sm` (0.25rem) para inputs.

### Inputs e Formulários
- **Base:** Fundo `surface_container_highest` (#e0e3e6) para inputs em repouso.
- **Foco:** Transição suave para uma borda `primary` de 2px, sem "glow" externo excessivo.
- **Micro-copy:** Helper texts devem usar `label-sm` em `on_surface_variant`.

### Botões (CTAs)
- **Primary:** Preenchimento `primary`, texto `on_primary`. Cantos arredondados `DEFAULT` (0.5rem).
- **Secondary:** Sem preenchimento, apenas texto `primary` com `surface_container_low` no hover.

---

## 6. Práticas Sugeridas (Do's and Don'ts)

### ✅ O que fazer (Do's)
- **Use o Espaço:** Se os dados estão densos, aumente o padding interno do card (`spacing.6`).
- **Hierarquia Visual:** Diferencie o rótulo do dado. O rótulo deve ser `label-sm` em `on_surface_variant`, e o valor em `title-sm` em `on_surface`.
- **Micro-interações:** Use transições suaves (200ms, ease-out) para todos os estados de hover e foco.

### ❌ O que evitar (Don'ts)
- **Não use divisores (Border-bottom):** Se os itens estão misturados, aumente o espaço entre eles.
- **Não use sombras em tudo:** Reserve sombras apenas para elementos que realmente sobrepõem outros (modais, tooltips).
- **Evite o Preto Puro:** Para textos, use `on_surface` (#191c1e). O contraste é alto o suficiente para acessibilidade, mas mais suave para os olhos.

---
*Este documento serve como a única fonte de verdade para a implementação visual desta interface. O rigor na aplicação destes princípios garantirá um produto final de classe mundial.*