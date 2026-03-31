import { Component, inject, signal, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { GadoService } from '../../core/services/gado.service';
import { InsumoService } from '../../core/services/insumo.service';
import { RacaoService } from '../../core/services/racao.service';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink],
  template: `
    <div class="p-8">
      <!-- Header -->
      <div class="mb-8">
        <h2 class="text-3xl font-bold text-on-surface font-headline">Dashboard</h2>
        <p class="text-on-surface-variant mt-1 text-sm">Visão geral da sua pecuária</p>
      </div>

      <!-- Stats Cards -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
        <!-- Card 1: Raças de Gado -->
        <div class="bg-surface-container-lowest p-6 rounded-xl hover:bg-primary/5 transition-colors cursor-default">
          <div class="flex justify-between items-start mb-4">
            <div class="p-2 bg-primary/10 rounded-lg text-primary">
              <span class="material-symbols-outlined">agriculture</span>
            </div>
          </div>
          <p class="text-on-surface-variant text-xs font-semibold uppercase tracking-wider">RAÇAS CADASTRADAS</p>
          <h3 class="text-3xl font-bold mt-1 font-headline text-on-surface">{{ gadoCount() }}</h3>
          <a routerLink="/gado" class="text-xs text-primary hover:text-primary-container mt-2 inline-block transition-colors">Ver todos →</a>
        </div>

        <!-- Card 2: Insumos -->
        <div class="bg-surface-container-lowest p-6 rounded-xl hover:bg-secondary/5 transition-colors cursor-default">
          <div class="flex justify-between items-start mb-4">
            <div class="p-2 bg-secondary/10 rounded-lg text-secondary">
              <span class="material-symbols-outlined">grass</span>
            </div>
          </div>
          <p class="text-on-surface-variant text-xs font-semibold uppercase tracking-wider">INSUMOS</p>
          <h3 class="text-3xl font-bold mt-1 font-headline text-on-surface">{{ insumoCount() }}</h3>
          <a routerLink="/insumo" class="text-xs text-secondary hover:opacity-80 mt-2 inline-block transition-colors">Ver todos →</a>
        </div>

        <!-- Card 3: Rações -->
        <div class="bg-surface-container-lowest p-6 rounded-xl hover:bg-tertiary/5 transition-colors cursor-default">
          <div class="flex justify-between items-start mb-4">
            <div class="p-2 bg-tertiary/10 rounded-lg text-tertiary">
              <span class="material-symbols-outlined">science</span>
            </div>
          </div>
          <p class="text-on-surface-variant text-xs font-semibold uppercase tracking-wider">RAÇÕES</p>
          <h3 class="text-3xl font-bold mt-1 font-headline text-on-surface">{{ racaoCount() }}</h3>
          <a routerLink="/racao" class="text-xs text-tertiary hover:opacity-80 mt-2 inline-block transition-colors">Ver todos →</a>
        </div>

        <!-- Card 4: Fórmulas Demonstrativas -->
        <div class="bg-surface-container-lowest p-6 rounded-xl hover:bg-primary/5 transition-colors cursor-default">
          <div class="flex justify-between items-start mb-4">
            <div class="p-2 bg-primary-container/20 rounded-lg text-primary">
              <span class="material-symbols-outlined">calculate</span>
            </div>
          </div>
          <p class="text-on-surface-variant text-xs font-semibold uppercase tracking-wider">FÓRMULAS DEMO</p>
          <h3 class="text-3xl font-bold mt-1 font-headline text-on-surface">6</h3>
          <a routerLink="/formulas" class="text-xs text-primary hover:text-primary-container mt-2 inline-block transition-colors">Visualizar →</a>
        </div>
      </div>

      <!-- Quick Access -->
      <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <!-- Quick Actions -->
        <div class="bg-surface-container-lowest rounded-xl p-6">
          <h3 class="text-base font-semibold text-on-surface font-headline mb-4">Acesso Rápido</h3>
          <div class="grid grid-cols-2 gap-3">
            @for (action of quickActions; track action.path) {
              <a [routerLink]="action.path"
                class="flex items-center gap-3 p-3 bg-surface-container-low rounded-lg hover:bg-surface-container transition-colors group">
                <span class="material-symbols-outlined text-on-surface-variant group-hover:text-primary transition-colors">{{ action.icon }}</span>
                <span class="text-sm text-on-surface font-medium">{{ action.label }}</span>
              </a>
            }
          </div>
        </div>

        <!-- Rendimento CTA -->
        <div class="bg-primary text-on-primary p-8 rounded-xl flex flex-col justify-between">
          <div>
            <span class="material-symbols-outlined text-4xl mb-4 block">trending_up</span>
            <h3 class="text-xl font-bold font-headline mb-2">Calcular Rendimento</h3>
            <p class="text-on-primary/80 text-sm leading-relaxed">
              Calcule a lucratividade do seu rebanho com base em peso, dieta e preço da arroba.
            </p>
          </div>
          <a routerLink="/rendimento"
            class="mt-6 inline-block w-full text-center py-3 bg-white/20 hover:bg-white/30 rounded-lg text-sm font-bold transition-all backdrop-blur-sm">
            Iniciar Cálculo
          </a>
        </div>
      </div>
    </div>
  `,
})
export class DashboardComponent implements OnInit {
  private readonly gadoService = inject(GadoService);
  private readonly insumoService = inject(InsumoService);
  private readonly racaoService = inject(RacaoService);

  readonly gadoCount = signal(0);
  readonly insumoCount = signal(0);
  readonly racaoCount = signal(0);

  readonly quickActions = [
    { path: '/gado', label: 'Gado', icon: 'agriculture' },
    { path: '/insumo', label: 'Insumos', icon: 'grass' },
    { path: '/racao', label: 'Rações', icon: 'science' },
    { path: '/manejo', label: 'Manejo', icon: 'manage_accounts' },
    { path: '/regiao', label: 'Região', icon: 'map' },
    { path: '/venda-racao', label: 'Venda Ração', icon: 'sell' },
  ];

  ngOnInit() {
    this.gadoService.listar().subscribe({
      next: (items) => this.gadoCount.set(items.length),
      error: () => {},
    });

    this.insumoService.listar().subscribe({
      next: (items) => this.insumoCount.set(items.length),
      error: () => {},
    });

    this.racaoService.listar().subscribe({
      next: (items) => this.racaoCount.set(items.length),
      error: () => {},
    });
  }
}
