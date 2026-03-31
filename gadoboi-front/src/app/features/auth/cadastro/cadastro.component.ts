import { Component, inject, signal } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

function senhaIgualValidator(control: AbstractControl): ValidationErrors | null {
  const senha = control.get('senha');
  const confirmSenha = control.get('confirmSenha');
  if (senha && confirmSenha && senha.value !== confirmSenha.value) {
    return { senhasDiferentes: true };
  }
  return null;
}

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  template: `
    <div class="min-h-screen bg-surface flex items-center justify-center p-4">
      <div class="w-full max-w-lg">
        <!-- Logo -->
        <div class="text-center mb-8">
          <h1 class="text-3xl font-bold text-primary font-headline">GadoBoi</h1>
          <p class="text-on-surface-variant mt-2 text-sm">Gestão pecuária inteligente</p>
        </div>

        <!-- Card -->
        <div class="bg-surface-container-lowest rounded-xl p-8 shadow-[0px_12px_32px_rgba(25,28,30,0.06)]">
          <h2 class="text-xl font-semibold text-on-surface font-headline mb-6">Criar conta</h2>

          @if (error()) {
            <div class="mb-4 p-3 bg-error-container rounded-lg text-on-error-container text-sm">
              {{ error() }}
            </div>
          }

          @if (success()) {
            <div class="mb-4 p-3 bg-tertiary/10 rounded-lg text-tertiary text-sm font-semibold">
              Conta criada com sucesso! Redirecionando para o login...
            </div>
          }

          <form [formGroup]="form" (ngSubmit)="onSubmit()" class="space-y-4">
            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">Nome</label>
                <input formControlName="nome" type="text" placeholder="João"
                  class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
                @if (form.get('nome')?.invalid && form.get('nome')?.touched) {
                  <p class="mt-1 text-xs text-error">Nome obrigatório</p>
                }
              </div>
              <div>
                <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">Sobrenome</label>
                <input formControlName="sobrenome" type="text" placeholder="Silva"
                  class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
                @if (form.get('sobrenome')?.invalid && form.get('sobrenome')?.touched) {
                  <p class="mt-1 text-xs text-error">Sobrenome obrigatório</p>
                }
              </div>
            </div>

            <div>
              <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">E-mail</label>
              <input formControlName="email" type="email" placeholder="seu@email.com"
                class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
              @if (form.get('email')?.invalid && form.get('email')?.touched) {
                <p class="mt-1 text-xs text-error">E-mail inválido</p>
              }
            </div>

            <div>
              <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">CPF</label>
              <input formControlName="cpf" type="text" placeholder="000.000.000-00"
                class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
              @if (form.get('cpf')?.invalid && form.get('cpf')?.touched) {
                <p class="mt-1 text-xs text-error">CPF obrigatório</p>
              }
            </div>

            <div class="grid grid-cols-2 gap-4">
              <div>
                <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">Telefone</label>
                <input formControlName="telefone" type="text" placeholder="(00) 0000-0000"
                  class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
              </div>
              <div>
                <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">Celular</label>
                <input formControlName="celular" type="text" placeholder="(00) 00000-0000"
                  class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
              </div>
            </div>

            <div>
              <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">Senha</label>
              <input formControlName="senha" type="password" placeholder="••••••••"
                class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
              @if (form.get('senha')?.invalid && form.get('senha')?.touched) {
                <p class="mt-1 text-xs text-error">Senha deve ter no mínimo 6 caracteres</p>
              }
            </div>

            <div>
              <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">Confirmar Senha</label>
              <input formControlName="confirmSenha" type="password" placeholder="••••••••"
                class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
              @if (form.errors?.['senhasDiferentes'] && form.get('confirmSenha')?.touched) {
                <p class="mt-1 text-xs text-error">As senhas não coincidem</p>
              }
            </div>

            <button type="submit" [disabled]="loading() || form.invalid"
              class="w-full py-2.5 bg-primary text-on-primary font-semibold rounded-lg hover:bg-primary-container transition-colors disabled:opacity-50 disabled:cursor-not-allowed mt-2">
              @if (loading()) { Criando conta... } @else { Criar Conta }
            </button>
          </form>

          <p class="mt-6 text-center text-sm text-on-surface-variant">
            Já tem conta?
            <a routerLink="/auth/login" class="text-primary font-semibold hover:text-primary-container transition-colors ml-1">Entrar</a>
          </p>
        </div>
      </div>
    </div>
  `,
})
export class CadastroComponent {
  private readonly fb = inject(FormBuilder);
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  readonly success = signal(false);

  readonly form = this.fb.group(
    {
      nome: ['', Validators.required],
      sobrenome: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      cpf: ['', Validators.required],
      telefone: [''],
      celular: [''],
      senha: ['', [Validators.required, Validators.minLength(6)]],
      confirmSenha: ['', Validators.required],
    },
    { validators: senhaIgualValidator }
  );

  onSubmit() {
    if (this.form.invalid) return;
    this.loading.set(true);
    this.error.set(null);

    const { nome, sobrenome, email, cpf, telefone, celular, senha } = this.form.value;
    this.authService
      .cadastro({
        nome: nome!,
        sobrenome: sobrenome!,
        email: email!,
        cpf: cpf!,
        telefone: telefone || undefined,
        celular: celular || undefined,
        senha: senha!,
      })
      .subscribe({
        next: () => {
          this.success.set(true);
          this.loading.set(false);
          setTimeout(() => this.router.navigate(['/auth/login']), 1500);
        },
        error: () => {
          this.error.set('Erro ao criar conta. Verifique os dados e tente novamente.');
          this.loading.set(false);
        },
      });
  }
}
