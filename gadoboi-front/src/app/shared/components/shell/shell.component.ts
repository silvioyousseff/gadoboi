import { Component, inject } from '@angular/core';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';
import { AuthStore } from '../../../core/services/auth.store';
import { Router } from '@angular/router';

@Component({
  selector: 'app-shell',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <div class="flex h-screen overflow-hidden bg-surface">
      <!-- Sidebar -->
      <aside class="w-64 h-full bg-surface-container-lowest flex flex-col py-6 px-4 flex-shrink-0 z-20">
        <!-- Logo -->
        <div class="mb-10 px-4">
          <h1 class="text-xl font-bold text-primary font-headline tracking-tight">GadoBoi</h1>
          <p class="text-xs text-on-surface-variant/70 mt-0.5">Gestão Pecuária</p>
        </div>
        <!-- Nav -->
        <nav class="flex-1 space-y-1 overflow-y-auto">
          @for (item of navItems; track item.path) {
            <a [routerLink]="item.path"
               routerLinkActive="text-primary border-l-4 border-primary bg-surface-container-low/50 font-semibold"
               [routerLinkActiveOptions]="{exact: item.exact ?? false}"
               class="flex items-center gap-3 px-4 py-2.5 rounded-lg text-on-surface-variant hover:bg-surface-container-low transition-all duration-200 text-sm">
              <span class="material-symbols-outlined text-[20px]">{{ item.icon }}</span>
              <span>{{ item.label }}</span>
            </a>
          }
        </nav>
        <!-- User -->
        <div class="mt-auto px-4 py-3 bg-surface-container-low rounded-xl flex items-center gap-3">
          <div class="w-9 h-9 rounded-full bg-primary/10 flex items-center justify-center text-primary font-bold text-sm font-headline">
            {{ userInitial() }}
          </div>
          <div class="flex-1 overflow-hidden">
            <p class="text-sm font-semibold truncate text-on-surface">{{ userName() }}</p>
            <p class="text-xs text-on-surface-variant truncate">Produtor</p>
          </div>
          <button (click)="logout()" title="Sair" class="text-on-surface-variant hover:text-error transition-colors">
            <span class="material-symbols-outlined text-[20px]">logout</span>
          </button>
        </div>
      </aside>

      <!-- Main Area -->
      <div class="flex-1 flex flex-col overflow-hidden">
        <!-- Top Bar -->
        <header class="h-14 bg-surface-container-lowest/80 backdrop-blur-md flex items-center justify-between px-8 flex-shrink-0">
          <div class="flex items-center gap-3">
            <div class="relative">
              <span class="material-symbols-outlined absolute left-3 top-1/2 -translate-y-1/2 text-on-surface-variant text-[18px]">search</span>
              <input type="text" placeholder="Buscar..."
                class="pl-9 pr-4 py-1.5 bg-surface-container-highest/50 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-primary/20 w-64 text-on-surface placeholder:text-on-surface-variant/60" />
            </div>
          </div>
          <div class="flex items-center gap-4 text-on-surface-variant">
            <button class="hover:text-on-surface transition-colors">
              <span class="material-symbols-outlined">notifications</span>
            </button>
          </div>
        </header>

        <!-- Content -->
        <main class="flex-1 overflow-y-auto">
          <router-outlet />
        </main>
      </div>
    </div>
  `,
})
export class ShellComponent {
  private readonly authStore = inject(AuthStore);
  private readonly router = inject(Router);

  readonly navItems = [
    { path: '/dashboard', label: 'Dashboard', icon: 'dashboard', exact: true },
    { path: '/gado', label: 'Gado', icon: 'agriculture' },
    { path: '/insumo', label: 'Insumos', icon: 'grass' },
    { path: '/racao', label: 'Rações', icon: 'science' },
    { path: '/venda-racao', label: 'Venda de Ração', icon: 'sell' },
    { path: '/manejo', label: 'Manejo', icon: 'manage_accounts' },
    { path: '/regiao', label: 'Região', icon: 'map' },
    { path: '/rendimento', label: 'Rendimento', icon: 'trending_up' },
    { path: '/formulas', label: 'Fórmulas', icon: 'calculate' },
  ];

  userName() {
    return 'Usuário';
  }

  userInitial() {
    return 'U';
  }

  logout() {
    this.authStore.clear();
    this.router.navigate(['/auth/login']);
  }
}
