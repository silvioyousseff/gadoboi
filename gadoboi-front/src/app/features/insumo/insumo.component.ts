import { Component, inject, signal, OnInit } from '@angular/core';
import { ReactiveFormsModule, FormBuilder, Validators } from '@angular/forms';
import { InsumoService } from '../../core/services/insumo.service';
import { Insumo } from '../../core/models';

@Component({
  selector: 'app-insumo',
  standalone: true,
  imports: [ReactiveFormsModule],
  template: `
    <div class="p-8">
      <!-- Header -->
      <div class="flex items-center justify-between mb-8">
        <div>
          <h2 class="text-3xl font-bold text-on-surface font-headline">Insumos</h2>
          <p class="text-on-surface-variant mt-1 text-sm">Gerencie os ingredientes para ração</p>
        </div>
        <button
          (click)="openCreate()"
          class="flex items-center gap-2 px-5 py-2.5 bg-primary text-on-primary font-semibold rounded-lg hover:bg-primary-container transition-colors text-sm"
        >
          <span class="material-symbols-outlined text-[18px]">add</span>
          Novo Insumo
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
            <span class="material-symbols-outlined text-5xl mb-3 opacity-30">grass</span>
            <p class="text-sm">Nenhum insumo cadastrado</p>
            <button
              (click)="openCreate()"
              class="mt-4 text-primary text-sm font-semibold hover:text-primary-container transition-colors"
            >
              Cadastrar primeiro insumo →
            </button>
          </div>
        } @else {
          <table class="w-full">
            <thead>
              <tr class="bg-surface-container-low">
                <th
                  class="text-left px-6 py-3 text-xs font-semibold text-on-surface-variant uppercase tracking-wider"
                >
                  Nome do Insumo
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
                    {{ item.nomeInsumo }}
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
        <div class="relative bg-surface-container-lowest rounded-xl p-6 w-full max-w-md shadow-2xl">
          <div class="flex items-center justify-between mb-5">
            <h3 class="text-lg font-semibold text-on-surface font-headline">
              {{ editItem() ? 'Editar Insumo' : 'Novo Insumo' }}
            </h3>
            <button
              (click)="closeModal()"
              class="p-1 text-on-surface-variant hover:text-on-surface rounded-lg transition-colors"
            >
              <span class="material-symbols-outlined">close</span>
            </button>
          </div>

          <form [formGroup]="form" (ngSubmit)="onSave()" class="space-y-4">
            <div>
              <label
                class="block text-xs font-semibold text-on-surface-variant mb-1.5 uppercase tracking-wide"
                >Nome do Insumo</label
              >
              <input
                formControlName="nomeInsumo"
                type="text"
                placeholder="Ex: Milho, Farelo de Soja, Ureia..."
                class="w-full px-4 py-2.5 bg-surface-container-highest rounded-lg text-sm text-on-surface placeholder:text-on-surface-variant/50 focus:outline-none focus:ring-2 focus:ring-primary/30 transition-all"
              />
              @if (form.get('nomeInsumo')?.invalid && form.get('nomeInsumo')?.touched) {
                <p class="mt-1 text-xs text-error">Nome obrigatório</p>
              }
            </div>

            <div class="flex gap-3 pt-2">
              <button
                type="button"
                (click)="closeModal()"
                class="flex-1 py-2.5 bg-surface-container-high text-on-surface font-semibold rounded-lg hover:bg-surface-container-highest transition-colors text-sm"
              >
                Cancelar
              </button>
              <button
                type="submit"
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
          </form>
        </div>
      </div>
    }
  `,
})
export class InsumoComponent implements OnInit {
  private readonly insumoService = inject(InsumoService);
  private readonly fb = inject(FormBuilder);

  readonly items = signal<Insumo[]>([]);
  readonly loading = signal(false);
  readonly savingItem = signal(false);
  readonly showModal = signal(false);
  readonly editItem = signal<Insumo | null>(null);
  readonly error = signal<string | null>(null);
  readonly toast = signal<string | null>(null);

  readonly form = this.fb.group({
    nomeInsumo: ['', Validators.required],
  });

  ngOnInit() {
    this.loadItems();
  }

  loadItems() {
    this.loading.set(true);
    this.error.set(null);
    this.insumoService.listar().subscribe({
      next: (data) => {
        this.items.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Erro ao carregar insumos.');
        this.loading.set(false);
      },
    });
  }

  openCreate() {
    this.editItem.set(null);
    this.form.reset();
    this.showModal.set(true);
  }

  openEdit(item: Insumo) {
    this.editItem.set(item);
    this.form.patchValue({ nomeInsumo: item.nomeInsumo });
    this.showModal.set(true);
  }

  closeModal() {
    this.showModal.set(false);
    this.editItem.set(null);
    this.form.reset();
  }

  onSave() {
    if (this.form.invalid) return;
    this.savingItem.set(true);
    this.error.set(null);

    const payload: Partial<Insumo> = {
      nomeInsumo: this.form.value.nomeInsumo!,
    };

    const item = this.editItem();
    const op = item?.id
      ? this.insumoService.atualizar(item.id, payload)
      : this.insumoService.criar(payload);

    op.subscribe({
      next: () => {
        this.savingItem.set(false);
        this.closeModal();
        this.loadItems();
        this.showToast(item?.id ? 'Insumo atualizado!' : 'Insumo cadastrado!');
      },
      error: () => {
        this.error.set('Erro ao salvar. Tente novamente.');
        this.savingItem.set(false);
      },
    });
  }

  onDelete(id: number) {
    if (!confirm('Deseja remover este insumo?')) return;
    this.insumoService.remover(id).subscribe({
      next: () => {
        this.loadItems();
        this.showToast('Insumo removido!');
      },
      error: () => this.error.set('Erro ao remover. Tente novamente.'),
    });
  }

  private showToast(msg: string) {
    this.toast.set(msg);
    setTimeout(() => this.toast.set(null), 3000);
  }
}
