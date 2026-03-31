import { Component, inject, signal } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { AuthStore } from '../../../core/services/auth.store';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  template: `
    <div class="min-h-screen bg-surface flex items-center justify-center p-4">
      <div class="w-full max-w-md">
        <!-- Logo -->
        <div class="text-center mb-8">
          <h1 class="text-3xl font-bold text-primary font-headline">GadoBoi</h1>
          <p class="text-on-surface-variant mt-2 text-sm">Gestão pecuária inteligente</p>
        </div>

        <!-- Card -->
        <div
          class="bg-surface-container-lowest rounded-xl p-8 shadow-[0px_12px_32px_rgba(25,28,30,0.06)]"
        >
          <h2 class="text-xl font-semibold text-on-surface font-headline mb-6">Entrar na conta</h2>

          @if (error()) {
            <div class="mb-4 p-3 bg-error-container rounded-lg text-on-error-container text-sm">
              {{ error() }}
            </div>
          }

          <form [formGroup]="form" (ngSubmit)="onSubmit()" class="space-y-4">
            <div>
              <label
                class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                >E-mail</label
              >
              <input
                formControlName="email"
                type="email"
                placeholder="seu@email.com"
                class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
              />
              @if (form.get('email')?.invalid && form.get('email')?.touched) {
                <p class="mt-1 text-xs text-error">E-mail inválido</p>
              }
            </div>

            <div>
              <label
                class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                >Senha</label
              >
              <input
                formControlName="senha"
                type="password"
                placeholder="••••••••"
                class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
              />
              @if (form.get('senha')?.invalid && form.get('senha')?.touched) {
                <p class="mt-1 text-xs text-error">Senha obrigatória</p>
              }
            </div>

            <button
              type="submit"
              [disabled]="loading() || form.invalid"
              class="w-full py-2.5 bg-primary text-on-primary font-semibold rounded-lg hover:bg-primary-container transition-colors disabled:opacity-50 disabled:cursor-not-allowed mt-2"
            >
              @if (loading()) {
                Entrando...
              } @else {
                Entrar
              }
            </button>
          </form>

          <p class="mt-6 text-center text-sm text-on-surface-variant">
            Não tem conta?
            <a
              routerLink="/auth/cadastro"
              class="text-primary font-semibold hover:text-primary-container transition-colors ml-1"
              >Cadastre-se</a
            >
          </p>
        </div>
      </div>
    </div>
  `,
})
export class LoginComponent {
  private readonly fb = inject(FormBuilder);
  private readonly authService = inject(AuthService);
  private readonly authStore = inject(AuthStore);
  private readonly router = inject(Router);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);

  readonly form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    senha: ['', Validators.required],
  });

  onSubmit() {
    if (this.form.invalid) return;
    this.loading.set(true);
    this.error.set(null);

    const { email, senha } = this.form.value;
    this.authService.login({ email: email!, senha: senha! }).subscribe({
      next: (res) => {
        this.authStore.setAuth(res);
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.error.set('E-mail ou senha inválidos.');
        this.loading.set(false);
      },
    });
  }
}
