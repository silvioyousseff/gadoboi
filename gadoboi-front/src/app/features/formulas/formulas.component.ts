import { Component } from '@angular/core';
import { DecimalPipe } from '@angular/common';

interface Ingrediente {
  nome: string;
  percentual: number;
}

interface Formula {
  nome: string;
  tipo: 'Confinamento' | 'Semi-confinamento' | 'Proteinato';
  ingredientes: Ingrediente[];
}

const FORMULAS: Formula[] = [
  {
    nome: 'Confinamento 1',
    tipo: 'Confinamento',
    ingredientes: [
      { nome: 'Milho', percentual: 78.34 },
      { nome: 'Farelo de Soja', percentual: 16.0 },
      { nome: 'Ureia', percentual: 2.04 },
      { nome: 'Sulfato de Amônio', percentual: 0.36 },
      { nome: 'Mineral Mix', percentual: 1.20 },
      { nome: 'Ionóforo', percentual: 0.06 },
      { nome: 'Cal Calcítica', percentual: 2.0 },
    ],
  },
  {
    nome: 'Confinamento 2',
    tipo: 'Confinamento',
    ingredientes: [
      { nome: 'Milho', percentual: 75.46 },
      { nome: 'Farelo de Soja', percentual: 20.0 },
      { nome: 'Ureia', percentual: 1.87 },
      { nome: 'Sulfato de Amônio', percentual: 0.33 },
      { nome: 'Mineral Mix', percentual: 1.0 },
      { nome: 'Ionóforo', percentual: 0.04 },
      { nome: 'Cal Calcítica', percentual: 1.30 },
    ],
  },
  {
    nome: 'Semi-confinamento Chuva',
    tipo: 'Semi-confinamento',
    ingredientes: [
      { nome: 'Milho', percentual: 73.95 },
      { nome: 'Farelo de Soja', percentual: 20.80 },
      { nome: 'Ureia', percentual: 1.70 },
      { nome: 'Sulfato de Amônio', percentual: 0.30 },
      { nome: 'Mineral Mix', percentual: 2.0 },
      { nome: 'Ionóforo', percentual: 0.05 },
      { nome: 'Cal Calcítica', percentual: 1.20 },
    ],
  },
  {
    nome: 'Semi-confinamento Estiagem',
    tipo: 'Semi-confinamento',
    ingredientes: [
      { nome: 'Milho', percentual: 70.86 },
      { nome: 'Farelo de Soja', percentual: 24.9 },
      { nome: 'Ureia', percentual: 1.28 },
      { nome: 'Sulfato de Amônio', percentual: 0.22 },
      { nome: 'Mineral Mix', percentual: 1.50 },
      { nome: 'Ionóforo', percentual: 0.04 },
      { nome: 'Cal Calcítica', percentual: 1.20 },
    ],
  },
  {
    nome: 'Proteinato Chuva',
    tipo: 'Proteinato',
    ingredientes: [
      { nome: 'Milho', percentual: 32.0 },
      { nome: 'Farelo de Soja', percentual: 25.0 },
      { nome: 'Ureia', percentual: 6.8 },
      { nome: 'Sulfato de Amônio', percentual: 1.2 },
      { nome: 'Mineral Mix', percentual: 15.0 },
      { nome: 'Sal Branco', percentual: 20.0 },
    ],
  },
  {
    nome: 'Proteinato Estiagem',
    tipo: 'Proteinato',
    ingredientes: [
      { nome: 'Milho', percentual: 22.0 },
      { nome: 'Farelo de Soja', percentual: 28.0 },
      { nome: 'Ureia', percentual: 12.8 },
      { nome: 'Sulfato de Amônio', percentual: 2.2 },
      { nome: 'Mineral Mix', percentual: 15.0 },
      { nome: 'Sal Branco', percentual: 20.0 },
    ],
  },
];

function tipoBadgeClass(tipo: Formula['tipo']): string {
  switch (tipo) {
    case 'Confinamento': return 'bg-primary/10 text-primary';
    case 'Semi-confinamento': return 'bg-secondary/10 text-secondary';
    case 'Proteinato': return 'bg-tertiary/10 text-tertiary';
  }
}

@Component({
  selector: 'app-formulas',
  standalone: true,
  imports: [DecimalPipe],
  template: `
    <div class="p-8">
      <!-- Header -->
      <div class="mb-8">
        <h2 class="text-3xl font-bold text-on-surface font-headline">Fórmulas Demonstrativas</h2>
        <p class="text-on-surface-variant mt-1 text-sm">
          Fórmulas padrão para os principais sistemas de criação de gado no Brasil
        </p>
      </div>

      <!-- Legend -->
      <div class="flex flex-wrap gap-3 mb-6">
        <div class="flex items-center gap-2 px-3 py-1.5 bg-primary/10 rounded-lg">
          <span class="w-2 h-2 rounded-full bg-primary"></span>
          <span class="text-xs font-semibold text-primary">Confinamento</span>
        </div>
        <div class="flex items-center gap-2 px-3 py-1.5 bg-secondary/10 rounded-lg">
          <span class="w-2 h-2 rounded-full bg-secondary"></span>
          <span class="text-xs font-semibold text-secondary">Semi-confinamento</span>
        </div>
        <div class="flex items-center gap-2 px-3 py-1.5 bg-tertiary/10 rounded-lg">
          <span class="w-2 h-2 rounded-full bg-tertiary"></span>
          <span class="text-xs font-semibold text-tertiary">Proteinato</span>
        </div>
      </div>

      <!-- Grid -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        @for (formula of formulas; track formula.nome) {
          <div class="bg-surface-container-lowest rounded-xl overflow-hidden hover:shadow-md transition-shadow">
            <!-- Card Header -->
            <div class="px-6 py-4 border-b border-surface-container-low flex items-center justify-between">
              <div>
                <h3 class="font-semibold text-on-surface font-headline">{{ formula.nome }}</h3>
                <p class="text-xs text-on-surface-variant mt-0.5">{{ formula.ingredientes.length }} ingredientes</p>
              </div>
              <span class="px-2.5 py-1 rounded-lg text-xs font-bold" [class]="tipoBadgeClass(formula.tipo)">
                {{ formula.tipo }}
              </span>
            </div>

            <!-- Ingredients Table -->
            <div class="px-6 py-4">
              <table class="w-full">
                <thead>
                  <tr>
                    <th class="text-left text-xs font-semibold text-on-surface-variant uppercase tracking-wider pb-2">Ingrediente</th>
                    <th class="text-right text-xs font-semibold text-on-surface-variant uppercase tracking-wider pb-2">%</th>
                  </tr>
                </thead>
                <tbody class="divide-y divide-surface-container-low">
                  @for (ing of formula.ingredientes; track ing.nome) {
                    <tr>
                      <td class="py-2 text-sm text-on-surface">{{ ing.nome }}</td>
                      <td class="py-2 text-right">
                        <div class="flex items-center justify-end gap-3">
                          <!-- Progress bar -->
                          <div class="w-20 h-1.5 bg-surface-container-high rounded-full overflow-hidden">
                            <div class="h-full rounded-full"
                              [class]="progressBarClass(formula.tipo)"
                              [style.width.%]="ing.percentual">
                            </div>
                          </div>
                          <span class="text-sm font-semibold text-on-surface w-12 text-right">
                            {{ ing.percentual | number:'1.0-2' }}%
                          </span>
                        </div>
                      </td>
                    </tr>
                  }
                </tbody>
                <tfoot>
                  <tr class="border-t-2 border-surface-container">
                    <td class="pt-2 text-xs font-bold text-on-surface-variant uppercase">Total</td>
                    <td class="pt-2 text-right text-sm font-bold text-on-surface">
                      {{ total(formula) | number:'1.0-2' }}%
                    </td>
                  </tr>
                </tfoot>
              </table>
            </div>
          </div>
        }
      </div>

      <!-- Info Note -->
      <div class="mt-8 p-4 bg-surface-container-low rounded-xl flex items-start gap-3">
        <span class="material-symbols-outlined text-on-surface-variant mt-0.5">info</span>
        <div>
          <p class="text-sm font-semibold text-on-surface">Sobre as Fórmulas Demonstrativas</p>
          <p class="text-xs text-on-surface-variant mt-1 leading-relaxed">
            Estas fórmulas são baseadas em padrões técnicos para alimentação suplementar do gado bovino brasileiro.
            Para uso comercial, consulte um zootecnista ou nutricionista animal para adequar as proporções
            às condições específicas do seu rebanho e região.
          </p>
        </div>
      </div>
    </div>
  `,
})
export class FormulasComponent {
  readonly formulas = FORMULAS;

  tipoBadgeClass(tipo: Formula['tipo']): string {
    return tipoBadgeClass(tipo);
  }

  progressBarClass(tipo: Formula['tipo']): string {
    switch (tipo) {
      case 'Confinamento': return 'bg-primary';
      case 'Semi-confinamento': return 'bg-secondary';
      case 'Proteinato': return 'bg-tertiary';
    }
  }

  total(formula: Formula): number {
    return formula.ingredientes.reduce((sum, i) => sum + i.percentual, 0);
  }
}
