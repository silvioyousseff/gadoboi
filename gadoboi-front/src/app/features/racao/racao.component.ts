import { Component, OnInit, inject, signal } from '@angular/core';
import { FormArray, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Gado, Insumo, Manejo, Racao, Regiao } from '../../core/models';
import { GadoService } from '../../core/services/gado.service';
import { InsumoService } from '../../core/services/insumo.service';
import { ManejoService } from '../../core/services/manejo.service';
import { RacaoService } from '../../core/services/racao.service';
import { RegiaoService } from '../../core/services/regiao.service';

@Component({
  selector: 'app-racao',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
    <div class="p-8">
      <!-- Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h2 class="text-3xl font-bold text-on-surface font-headline">Rações</h2>
          <p class="text-on-surface-variant mt-1 text-sm">Gerencie as fórmulas de ração</p>
        </div>
        <button
          (click)="openCreate()"
          class="flex items-center gap-2 px-5 py-2.5 bg-primary text-on-primary font-semibold rounded-lg hover:bg-primary-container transition-colors text-sm"
        >
          <span class="material-symbols-outlined text-[18px]">add</span>
          Nova Ração
        </button>
      </div>

      <!-- Toast -->
      @if (toast()) {
        <div
          class="fixed top-6 right-6 z-50 px-5 py-3 bg-tertiary text-on-tertiary rounded-lg shadow-lg text-sm font-semibold animate-pulse"
        >
          {{ toast() }}
        </div>
      }

      <!-- Error -->
      @if (error()) {
        <div class="mb-4 p-3 bg-error-container rounded-lg text-on-error-container text-sm">
          {{ error() }}
        </div>
      }

      <!-- Table -->
      <div class="bg-surface-container-lowest rounded-xl overflow-hidden">
        @if (loading()) {
          <div class="flex items-center justify-center py-16 text-on-surface-variant">
            <span class="material-symbols-outlined animate-spin mr-2">progress_activity</span>
            Carregando...
          </div>
        } @else if (items().length === 0) {
          <div class="flex flex-col items-center justify-center py-16 text-on-surface-variant">
            <span class="material-symbols-outlined text-5xl mb-3 opacity-30">science</span>
            <p class="text-sm">Nenhuma ração cadastrada</p>
            <button
              (click)="openCreate()"
              class="mt-4 text-primary text-sm font-semibold hover:text-primary-container transition-colors"
            >
              Cadastrar primeira ração →
            </button>
          </div>
        } @else {
          <table class="w-full">
            <thead>
              <tr class="bg-surface-container-low">
                <th
                  class="text-left px-6 py-3 text-xs font-semibold text-on-surface-variant uppercase tracking-wider"
                >
                  Nome
                </th>
                <th
                  class="text-left px-6 py-3 text-xs font-semibold text-on-surface-variant uppercase tracking-wider"
                >
                  Status
                </th>
                <th
                  class="text-left px-6 py-3 text-xs font-semibold text-on-surface-variant uppercase tracking-wider"
                >
                  Gado
                </th>
                <th
                  class="text-left px-6 py-3 text-xs font-semibold text-on-surface-variant uppercase tracking-wider"
                >
                  Região
                </th>
                <th
                  class="text-right px-6 py-3 text-xs font-semibold text-on-surface-variant uppercase tracking-wider"
                >
                  Ações
                </th>
              </tr>
            </thead>
            <tbody class="divide-y divide-surface-container-low">
              @for (item of items(); track item.id) {
                <tr class="hover:bg-surface-container-low/50 transition-colors">
                  <td class="px-6 py-4 text-sm text-on-surface font-medium">
                    {{ item.nomeRacao }}
                  </td>
                  <td class="px-6 py-4">
                    @if (item.status) {
                      <span
                        class="px-2 py-0.5 bg-secondary/10 text-secondary rounded text-xs font-semibold"
                        >Venda</span
                      >
                    } @else {
                      <span
                        class="px-2 py-0.5 bg-tertiary/10 text-tertiary rounded text-xs font-semibold"
                        >Interno</span
                      >
                    }
                  </td>
                  <td class="px-6 py-4 text-sm text-on-surface-variant">
                    {{ gadoNome(item.gadoId) }}
                  </td>
                  <td class="px-6 py-4 text-sm text-on-surface-variant">
                    {{ regiaoNome(item.regiaoId) }}
                  </td>
                  <td class="px-6 py-4 text-right">
                    <div class="flex items-center justify-end gap-2">
                      <button
                        (click)="openEdit(item)"
                        class="p-1.5 text-on-surface-variant hover:text-secondary hover:bg-secondary/10 rounded-lg transition-colors"
                      >
                        <span class="material-symbols-outlined text-[18px]">edit</span>
                      </button>
                      <button
                        (click)="onDelete(item.id!)"
                        class="p-1.5 text-on-surface-variant hover:text-error hover:bg-error/10 rounded-lg transition-colors"
                      >
                        <span class="material-symbols-outlined text-[18px]">delete</span>
                      </button>
                    </div>
                  </td>
                </tr>
              }
            </tbody>
          </table>
        }
      </div>
    </div>

    <!-- Modal -->
    @if (showModal()) {
      <div class="fixed inset-0 z-50 flex items-center justify-center p-4">
        <div class="absolute inset-0 bg-black/40 backdrop-blur-sm" (click)="closeModal()"></div>
        <div
          class="relative bg-surface-container-lowest rounded-xl w-full max-w-2xl shadow-2xl flex flex-col max-h-[90vh]"
        >
          <!-- Modal Header -->
          <div
            class="flex items-center justify-between px-6 py-5 border-b border-surface-container-low flex-shrink-0"
          >
            <h3 class="text-lg font-semibold text-on-surface font-headline">
              {{ editItem() ? 'Editar Ração' : 'Nova Ração' }}
            </h3>
            <button
              (click)="closeModal()"
              class="p-1 text-on-surface-variant hover:text-on-surface rounded-lg transition-colors"
            >
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>

          <!-- Modal Body (scrollable) -->
          <div class="overflow-y-auto flex-1 px-6 py-5">
            <form [formGroup]="form" class="space-y-6">
              <!-- Section 1: Basic Info -->
              <div>
                <h4 class="text-xs font-bold text-on-surface-variant uppercase tracking-wider mb-3">
                  Informações Básicas
                </h4>
                <div class="space-y-3">
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Nome da Ração</label
                    >
                    <input
                      formControlName="nomeRacao"
                      type="text"
                      placeholder="Ex: Ração Verão Nelore..."
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    />
                    @if (form.get('nomeRacao')?.invalid && form.get('nomeRacao')?.touched) {
                      <p class="mt-1 text-xs text-error">Nome obrigatório</p>
                    }
                  </div>
                </div>
              </div>

              <!-- Section 2: Links -->
              <div>
                <h4 class="text-xs font-bold text-on-surface-variant uppercase tracking-wider mb-3">
                  Vínculo
                </h4>
                <div class="grid grid-cols-3 gap-3">
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Gado</label
                    >
                    <select
                      formControlName="gadoId"
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    >
                      <option value="">Selecione</option>
                      @for (g of gados(); track g.id) {
                        <option [value]="g.id">{{ g.nomeGado }}</option>
                      }
                    </select>
                  </div>
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Região</label
                    >
                    <select
                      formControlName="regiaoId"
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    >
                      <option value="">Selecione</option>
                      @for (r of regioes(); track r.id) {
                        <option [value]="r.id">{{ r.nomeRegiao }}</option>
                      }
                    </select>
                  </div>
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Manejo</label
                    >
                    <select
                      formControlName="manejoId"
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    >
                      <option value="">Selecione</option>
                      @for (m of manejos(); track m.id) {
                        <option [value]="m.id">{{ m.nomeManejo }}</option>
                      }
                    </select>
                  </div>
                </div>
              </div>

              <!-- Section 3: Quantities -->
              <div>
                <h4 class="text-xs font-bold text-on-surface-variant uppercase tracking-wider mb-3">
                  Quantidades
                </h4>
                <div class="grid grid-cols-2 gap-3">
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Qtd. de Gado</label
                    >
                    <input
                      formControlName="qtdGado"
                      type="number"
                      min="0"
                      placeholder="0"
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    />
                  </div>
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Tratamentos/dia</label
                    >
                    <input
                      formControlName="qtdTratamentoDia"
                      type="number"
                      min="0"
                      placeholder="0"
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    />
                  </div>
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Peso Inicial (kg)</label
                    >
                    <input
                      formControlName="pesoInicial"
                      type="number"
                      min="0"
                      step="0.1"
                      placeholder="0"
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    />
                  </div>
                  <div>
                    <label
                      class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                      >Taxa de Rendimento (%)</label
                    >
                    <input
                      formControlName="taxaRendimento"
                      type="number"
                      min="0"
                      max="100"
                      step="0.01"
                      placeholder="0"
                      class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                    />
                  </div>
                </div>
              </div>

              <!-- Section 4: Ingredients -->
              <div>
                <div class="flex items-center justify-between mb-3">
                  <h4 class="text-xs font-bold text-on-surface-variant uppercase tracking-wider">
                    Ingredientes
                  </h4>
                  <button
                    type="button"
                    (click)="addInsumo()"
                    class="text-xs text-primary font-semibold hover:text-primary-container transition-colors flex items-center gap-1"
                  >
                    <span class="material-symbols-outlined text-[16px]">add</span>
                    Adicionar Insumo
                  </button>
                </div>
                <div class="space-y-2" formArrayName="insumos">
                  @for (ctrl of insumosArray.controls; track $index) {
                    <div [formGroupName]="$index" class="flex gap-2 items-start">
                      <select
                        formControlName="insumoId"
                        class="flex-1 px-3 py-2 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                      >
                        <option value="">Selecione insumo</option>
                        @for (ins of insumos(); track ins.id) {
                          <option [value]="ins.id">{{ ins.nomeInsumo }}</option>
                        }
                      </select>
                      <input
                        formControlName="qtdInsumo"
                        type="number"
                        min="0"
                        step="0.01"
                        placeholder="Qtd (kg)"
                        class="w-28 px-3 py-2 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                      />
                      <button
                        type="button"
                        (click)="removeInsumo($index)"
                        class="p-2 text-on-surface-variant hover:text-error hover:bg-error/10 rounded-lg transition-colors flex-shrink-0"
                      >
                        <span class="material-symbols-outlined text-[18px]">remove_circle</span>
                      </button>
                    </div>
                  }
                  @if (insumosArray.length === 0) {
                    <p class="text-xs text-on-surface-variant/60 italic py-2">
                      Nenhum ingrediente adicionado ainda.
                    </p>
                  }
                </div>
              </div>

              <!-- Section 5: Methods -->
              <div>
                <div class="flex items-center justify-between mb-3">
                  <h4 class="text-xs font-bold text-on-surface-variant uppercase tracking-wider">
                    Métodos
                  </h4>
                  <button
                    type="button"
                    (click)="addMetodo()"
                    class="text-xs text-primary font-semibold hover:text-primary-container transition-colors flex items-center gap-1"
                  >
                    <span class="material-symbols-outlined text-[16px]">add</span>
                    Adicionar Método
                  </button>
                </div>
                <div class="space-y-2" formArrayName="metodos">
                  @for (ctrl of metodosArray.controls; track $index) {
                    <div class="flex gap-2 items-center">
                      <input
                        [formControlName]="$index"
                        type="text"
                        placeholder="Descreva o método..."
                        class="flex-1 px-3 py-2 bg-surface-container-highest rounded-lg text-sm text-on-surface focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
                      />
                      <button
                        type="button"
                        (click)="removeMetodo($index)"
                        class="p-2 text-on-surface-variant hover:text-error hover:bg-error/10 rounded-lg transition-colors flex-shrink-0"
                      >
                        <span class="material-symbols-outlined text-[18px]">remove_circle</span>
                      </button>
                    </div>
                  }
                  @if (metodosArray.length === 0) {
                    <p class="text-xs text-on-surface-variant/60 italic py-2">
                      Nenhum método adicionado ainda.
                    </p>
                  }
                </div>
              </div>
            </form>
          </div>

          <!-- Modal Footer -->
          <div class="flex gap-3 px-6 py-4 border-t border-surface-container-low flex-shrink-0">
            <button
              type="button"
              (click)="closeModal()"
              class="flex-1 py-2.5 bg-surface-container-high text-on-surface font-semibold rounded-lg hover:bg-surface-container-highest transition-colors text-sm"
            >
              Cancelar
            </button>
            <button
              type="button"
              (click)="onSave()"
              [disabled]="savingItem() || form.invalid"
              class="flex-1 py-2.5 bg-primary text-on-primary font-semibold rounded-lg hover:bg-primary-container transition-colors disabled:opacity-50 disabled:cursor-not-allowed text-sm"
            >
              @if (savingItem()) {
                Salvando...
              } @else {
                Salvar
              }
            </button>
          </div>
        </div>
      </div>
    }
  `,
})
export class RacaoComponent implements OnInit {
  private readonly racaoService = inject(RacaoService);
  private readonly gadoService = inject(GadoService);
  private readonly insumoService = inject(InsumoService);
  private readonly manejoService = inject(ManejoService);
  private readonly regiaoService = inject(RegiaoService);
  private readonly fb = inject(FormBuilder);

  readonly items = signal<Racao[]>([]);
  readonly gados = signal<Gado[]>([]);
  readonly insumos = signal<Insumo[]>([]);
  readonly manejos = signal<Manejo[]>([]);
  readonly regioes = signal<Regiao[]>([]);

  readonly loading = signal(false);
  readonly savingItem = signal(false);
  readonly showModal = signal(false);
  readonly editItem = signal<Racao | null>(null);
  readonly error = signal<string | null>(null);
  readonly toast = signal<string | null>(null);

  readonly form = this.fb.group({
    nomeRacao: ['', Validators.required],
    gadoId: [''],
    regiaoId: [''],
    manejoId: [''],
    qtdGado: [null as number | null],
    qtdTratamentoDia: [null as number | null],
    pesoInicial: [null as number | null],
    taxaRendimento: [null as number | null],
    insumos: this.fb.array([]),
    metodos: this.fb.array([]),
    status: false
  });

  get insumosArray(): FormArray {
    return this.form.get('insumos') as FormArray;
  }

  get metodosArray(): FormArray {
    return this.form.get('metodos') as FormArray;
  }

  gadoNome(id?: number): string {
    if (!id) return '-';
    return this.gados().find((g) => g.id === id)?.nomeGado ?? '-';
  }

  regiaoNome(id?: number): string {
    if (!id) return '-';
    return this.regioes().find((r) => r.id === id)?.nomeRegiao ?? '-';
  }

  ngOnInit() {
    this.loadItems();
    this.loadSelects();
  }

  loadItems() {
    this.loading.set(true);
    this.error.set(null);
    this.racaoService.listar().subscribe({
      next: (data) => {
        this.items.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Erro ao carregar rações.');
        this.loading.set(false);
      },
    });
  }

  loadSelects() {
    this.gadoService.listar().subscribe({ next: (d) => this.gados.set(d), error: () => {} });
    this.insumoService.listar().subscribe({ next: (d) => this.insumos.set(d), error: () => {} });
    this.manejoService.listar().subscribe({ next: (d) => this.manejos.set(d), error: () => {} });
    this.regiaoService.listar().subscribe({ next: (d) => this.regioes.set(d), error: () => {} });
  }

  addInsumo() {
    this.insumosArray.push(
      this.fb.group({
        insumoId: ['', Validators.required],
        qtdInsumo: [null, Validators.required],
      }),
    );
  }

  removeInsumo(index: number) {
    this.insumosArray.removeAt(index);
  }

  addMetodo() {
    this.metodosArray.push(this.fb.control(''));
  }

  removeMetodo(index: number) {
    this.metodosArray.removeAt(index);
  }

  openCreate() {
    this.editItem.set(null);
    this.form.reset();
    while (this.insumosArray.length) this.insumosArray.removeAt(0);
    while (this.metodosArray.length) this.metodosArray.removeAt(0);
    this.showModal.set(true);
  }

  openEdit(item: Racao) {
    this.editItem.set(item);
    while (this.insumosArray.length) this.insumosArray.removeAt(0);
    while (this.metodosArray.length) this.metodosArray.removeAt(0);

    this.form.patchValue({
      nomeRacao: item.nomeRacao,
      gadoId: item.gadoId ? String(item.gadoId) : '',
      regiaoId: item.regiaoId ? String(item.regiaoId) : '',
      manejoId: item.manejoId ? String(item.manejoId) : '',
      qtdGado: item.qtdGado ?? null,
      qtdTratamentoDia: item.qtdTratamentoDia ?? null,
      pesoInicial: item.pesoInicial ?? null,
      taxaRendimento: item.taxaRendimento ?? null,
      status: false
    });

    item.insumos?.forEach((ins) => {
      this.insumosArray.push(
        this.fb.group({
          insumoId: [String(ins.insumoId), Validators.required],
          qtdInsumo: [ins.qtdInsumo, Validators.required],
        }),
      );
    });

    item.metodos?.forEach((m) => {
      this.metodosArray.push(this.fb.control(m));
    });

    this.showModal.set(true);
  }

  closeModal() {
    this.showModal.set(false);
    this.editItem.set(null);
    this.form.reset();
    while (this.insumosArray.length) this.insumosArray.removeAt(0);
    while (this.metodosArray.length) this.metodosArray.removeAt(0);
  }

  onSave() {
    if (this.form.get('nomeRacao')?.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.savingItem.set(true);
    this.error.set(null);

    const v = this.form.value;
    const payload: Partial<Racao> = {
      nomeRacao: v.nomeRacao!,
      gadoId: v.gadoId ? Number(v.gadoId) : undefined,
      regiaoId: v.regiaoId ? Number(v.regiaoId) : undefined,
      manejoId: v.manejoId ? Number(v.manejoId) : undefined,
      qtdGado: v.qtdGado ?? undefined,
      qtdTratamentoDia: v.qtdTratamentoDia ?? undefined,
      pesoInicial: v.pesoInicial ?? undefined,
      taxaRendimento: v.taxaRendimento ?? undefined,
      insumos:
        (v.insumos as any[])?.map((i) => ({
          insumoId: Number(i.insumoId),
          qtdInsumo: Number(i.qtdInsumo),
        })) ?? [],
      metodos: (v.metodos as string[])?.filter((m) => !!m) ?? [],
      status: false
    };

    const item = this.editItem();
    const op = item?.id
      ? this.racaoService.atualizar(item.id, payload)
      : this.racaoService.criar(payload);

    op.subscribe({
      next: () => {
        this.savingItem.set(false);
        this.closeModal();
        this.loadItems();
        this.showToast(item?.id ? 'Ração atualizada!' : 'Ração cadastrada!');
      },
      error: () => {
        this.error.set('Erro ao salvar. Tente novamente.');
        this.savingItem.set(false);
      },
    });
  }

  onDelete(id: number) {
    if (!confirm('Deseja remover esta ração?')) return;
    this.racaoService.remover(id).subscribe({
      next: () => {
        this.loadItems();
        this.showToast('Ração removida!');
      },
      error: () => this.error.set('Erro ao remover. Tente novamente.'),
    });
  }

  private showToast(msg: string) {
    this.toast.set(msg);
    setTimeout(() => this.toast.set(null), 3000);
  }
}
