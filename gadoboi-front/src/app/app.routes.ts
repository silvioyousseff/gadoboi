import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  {
    path: 'auth',
    children: [
      {
        path: 'login',
        loadComponent: () => import('./features/auth/login/login.component').then(m => m.LoginComponent),
      },
      {
        path: 'cadastro',
        loadComponent: () => import('./features/auth/cadastro/cadastro.component').then(m => m.CadastroComponent),
      },
    ],
  },
  {
    path: '',
    loadComponent: () => import('./shared/components/shell/shell.component').then(m => m.ShellComponent),
    canActivate: [authGuard],
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./features/dashboard/dashboard.component').then(m => m.DashboardComponent),
      },
      {
        path: 'gado',
        loadComponent: () => import('./features/gado/gado.component').then(m => m.GadoComponent),
      },
      {
        path: 'insumo',
        loadComponent: () => import('./features/insumo/insumo.component').then(m => m.InsumoComponent),
      },
      {
        path: 'racao',
        loadComponent: () => import('./features/racao/racao.component').then(m => m.RacaoComponent),
      },
      {
        path: 'manejo',
        loadComponent: () => import('./features/manejo/manejo.component').then(m => m.ManejoComponent),
      },
      {
        path: 'regiao',
        loadComponent: () => import('./features/regiao/regiao.component').then(m => m.RegiaoComponent),
      },
      {
        path: 'rendimento',
        loadComponent: () => import('./features/rendimento/rendimento.component').then(m => m.RendimentoComponent),
      },
      {
        path: 'venda-racao',
        loadComponent: () => import('./features/venda-racao/venda-racao.component').then(m => m.VendaRacaoComponent),
      },
      {
        path: 'formulas',
        loadComponent: () => import('./features/formulas/formulas.component').then(m => m.FormulasComponent),
      },
    ],
  },
  { path: '**', redirectTo: 'dashboard' },
];
