import { Component, inject, signal, computed } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { CurrencyPipe } from '@angular/common';
import { RendimentoService } from '../../core/services/rendimento.service';
import { Rendimento } from '../../core/models';

@Component({
  selector: 'app-rendimento',
  standalone: true,
  imports: [ReactiveFormsModule, CurrencyPipe],
  template: `
    <div class="p-8 max-w-5xl">
      <div class="mb-8">
        <h2 class="text-3xl font-bold text-on-surface font-headline">Calculadora de Rendimento</h2>
        <p class="text-on-surface-variant mt-1 text-sm">Calcule a lucratividade do seu rebanho</p>
      </div>

      <div class="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <!-- Form -->
        <div class="bg-surface-container-lowest rounded-xl p-6">
          <h3 class="font-semibold text-on-surface font-headline mb-4">Parâmetros</h3>

          @if (error()) {
            <div class="mb-4 p-3 bg-error-container rounded-lg text-on-error-container text-sm">
              {{ error() }}
            </div>
          }

          <form [formGroup]="form" (ngSubmit)="calcular()" class="space-y-4">
            @for (field of formFields; track field.key) {
              <div>
                <label class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide">
                  {{ field.label }}
                </label>
                <input [formControlName]="field.key" type="number" [step]="field.step || 1" [placeholder]="field.placeholder || '0'"
                  class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all" />
                @if (form.get(field.key)?.invalid && form.get(field.key)?.touched) {
                  <p class="mt-1 text-xs text-error">Campo obrigatório</p>
                }
              </div>
            }
            <button type="submit" [disabled]="loading() || form.invalid"
              class="w-full py-2.5 bg-primary text-on-primary font-semibold rounded-lg hover:bg-primary-container transition-colors disabled:opacity-50 disabled:cursor-not-allowed mt-2">
              @if (loading()) { Calculando... } @else { Calcular Rendimento }
            </button>
          </form>
        </div>

        <!-- Results -->
        <div class="bg-surface-container-lowest rounded-xl p-6">
          <h3 class="font-semibold text-on-surface font-headline mb-4">Resultado</h3>
          @if (!resultado()) {
            <div class="flex flex-col items-center justify-center h-64 text-on-surface-variant">
              <span class="material-symbols-outlined text-5xl mb-3 opacity-30">calculate</span>
              <p class="text-sm">Preencha os parâmetros e calcule</p>
            </div>
          } @else {
            <div class="space-y-3">
              @for (item of resultItems(); track item.label) {
                <div class="flex justify-between items-center py-2 border-b border-surface-container-low">
                  <span class="text-xs font-semibold text-on-surface-variant uppercase tracking-wide">{{ item.label }}</span>
                  <span class="text-sm font-semibold text-on-surface">{{ item.value }}</span>
                </div>
              }
              <!-- Resultado Final -->
              <div class="mt-4 pt-4">
                <div class="flex justify-between items-center">
                  <span class="text-sm font-bold text-on-surface uppercase tracking-wide">RESULTADO</span>
                  <span class="text-xl font-bold font-headline"
                    [class]="resultado()!.resultado >= 0 ? 'text-tertiary' : 'text-error'">
                    {{ resultado()!.resultado | currency:'BRL':'symbol':'1.2-2':'pt-BR' }}
                  </span>
                </div>
                <p class="text-xs text-on-surface-variant mt-1 text-right">
                  {{ resultado()!.resultado >= 0 ? 'Operação lucrativa' : 'Operação deficitária' }}
                </p>
                <!-- Summary card -->
                <div class="mt-4 p-4 rounded-xl"
                  [class]="resultado()!.resultado >= 0 ? 'bg-tertiary/10' : 'bg-error-container'">
                  <div class="flex items-center gap-2">
                    <span class="material-symbols-outlined text-[22px]"
                      [class]="resultado()!.resultado >= 0 ? 'text-tertiary' : 'text-error'">
                      {{ resultado()!.resultado >= 0 ? 'trending_up' : 'trending_down' }}
                    </span>
                    <span class="text-sm font-semibold"
                      [class]="resultado()!.resultado >= 0 ? 'text-on-tertiary-container' : 'text-on-error-container'">
                      {{ resultado()!.resultado >= 0 ? 'Parabéns! Esta operação gera lucro.' : 'Atenção: esta operação gera prejuízo.' }}
                    </span>
                  </div>
                </div>
              </div>
            </div>
          }
        </div>
      </div>
    </div>
  `,
})
export class RendimentoComponent {
  private readonly rendimentoService = inject(RendimentoService);
  private readonly fb = inject(FormBuilder);

  readonly loading = signal(false);
  readonly error = signal<string | null>(null);
  readonly resultado = signal<Rendimento | null>(null);

  readonly formFields = [
    { key: 'qtdAnimais', label: 'Qtd. de Animais', placeholder: '100', step: 1 },
    { key: 'periodoDeTratamento', label: 'Período de Tratamento (dias)', placeholder: '90', step: 1 },
    { key: 'tamanhoDaPastagem', label: 'Tamanho da Pastagem (ha)', placeholder: '10', step: 0.01 },
    { key: 'pesoInicial', label: 'Peso Inicial (kg)', placeholder: '350', step: 0.1 },
    { key: 'ganhoDePesoEsperado', label: 'Ganho de Peso Esperado (kg/dia)', placeholder: '1.2', step: 0.01 },
    { key: 'rendimentoCarcaca', label: 'Rendimento de Carcaça (%)', placeholder: '55', step: 0.01 },
    { key: 'precoArroba', label: 'Preço da Arroba (R$)', placeholder: '300', step: 0.01 },
    { key: 'precoPorQuiloCon', label: 'Preço/kg do Concentrado (R$)', placeholder: '1.50', step: 0.001 },
  ];

  readonly form = this.fb.group({
    qtdAnimais: [null, [Validators.required, Validators.min(1)]],
    periodoDeTratamento: [null, [Validators.required, Validators.min(1)]],
    tamanhoDaPastagem: [null, [Validators.required, Validators.min(0)]],
    pesoInicial: [null, [Validators.required, Validators.min(0)]],
    ganhoDePesoEsperado: [null, [Validators.required, Validators.min(0)]],
    rendimentoCarcaca: [null, [Validators.required, Validators.min(0), Validators.max(100)]],
    precoArroba: [null, [Validators.required, Validators.min(0)]],
    precoPorQuiloCon: [null, [Validators.required, Validators.min(0)]],
  });

  readonly resultItems = computed(() => {
    const r = this.resultado();
    if (!r) return [];
    return [
      { label: 'Taxa de Lotação', value: `${(r.taxaLotacao ?? 0).toFixed(2)} cab/ha` },
      { label: 'Concentrado/dia (total)', value: `${(r.qtdConcentradoDiariamente ?? 0).toFixed(2)} kg` },
      { label: 'Concentrado Total', value: `${(r.qtdConcentradoTotal ?? 0).toFixed(2)} kg` },
      { label: 'Custo Total', value: this.formatCurrency(r.custoTotal ?? 0) },
      { label: 'Preço Concentrado/dia/animal', value: this.formatCurrency(r.precoConcentradoDiaAnimal ?? 0) },
      { label: 'Ganho por Cabeça/dia', value: `${(r.ganhoPorCabecaDia ?? 0).toFixed(3)} kg` },
      { label: 'Ganho Total', value: this.formatCurrency(r.ganhoTotal ?? 0) },
    ];
  });

  private formatCurrency(value: number): string {
    return value.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
  }

  calcular() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.loading.set(true);
    this.error.set(null);

    const v = this.form.value as any;
    this.rendimentoService.calcular({
      qtdAnimais: Number(v.qtdAnimais),
      periodoDeTratamento: Number(v.periodoDeTratamento),
      tamanhoDaPastagem: Number(v.tamanhoDaPastagem),
      pesoInicial: Number(v.pesoInicial),
      ganhoDePesoEsperado: Number(v.ganhoDePesoEsperado),
      rendimentoCarcaca: Number(v.rendimentoCarcaca),
      precoArroba: Number(v.precoArroba),
      precoPorQuiloCon: Number(v.precoPorQuiloCon),
    }).subscribe({
      next: (res) => {
        this.resultado.set(res);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Erro ao calcular rendimento. Verifique os dados e tente novamente.');
        this.loading.set(false);
      },
    });
  }
}
