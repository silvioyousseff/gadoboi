import { Component, inject, OnInit, signal } from '@angular/core';
import { FormArray, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Gado, Insumo, Manejo, Racao, Regiao } from '../../core/models';
import { GadoService } from '../../core/services/gado.service';
import { InsumoService } from '../../core/services/insumo.service';
import { ManejoService } from '../../core/services/manejo.service';
import { RacaoService } from '../../core/services/racao.service';
import { RegiaoService } from '../../core/services/regiao.service';

@Component({
  selector: 'app-venda-racao',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './venda-racao.component.html',
})
export class VendaRacaoComponent implements OnInit {
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
  readonly showModalVenda = signal(false);
  readonly editItem = signal<Racao | null>(null);
  readonly vendaItem = signal<Racao | null>(null);
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
    status: true
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
    this.racaoService.listarVenda().subscribe({
      next: (data) => {
        this.items.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Erro ao carregar rações de venda.');
        this.loading.set(false);
      },
    });
  }

  loadItem(id: number) {
    this.loading.set(true);
    this.error.set(null);
    this.racaoService.buscar(id).subscribe({
      next: (data) => {
        this.vendaItem.set(data);
        this.loading.set(false);
      },
      error: () => {
        this.error.set('Erro ao carregar ração de venda.');
        this.loading.set(false);
      },
    });
  }

  loadSelects() {
    this.gadoService.listar().subscribe({ next: (d) => this.gados.set(d), error: () => { } });
    this.insumoService.listar().subscribe({ next: (d) => this.insumos.set(d), error: () => { } });
    this.manejoService.listar().subscribe({ next: (d) => this.manejos.set(d), error: () => { } });
    this.regiaoService.listar().subscribe({ next: (d) => this.regioes.set(d), error: () => { } });
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

  openVenda(item: Racao) {
    this.loadItem(item.id!);
    this.showModalVenda.set(true);
  }

  closeModalVenda() {
    this.showModalVenda.set(false);
    this.vendaItem.set(null);
  }

  closeModal() {
    this.showModal.set(false);
    this.editItem.set(null);
    this.vendaItem.set(null);
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
      status: true,
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
        this.showToast(item?.id ? 'Ração atualizada!' : 'Ração de venda cadastrada!');
      },
      error: () => {
        this.error.set('Erro ao salvar. Tente novamente.');
        this.savingItem.set(false);
      },
    });
  }

  onDelete(id: number) {
    if (!confirm('Deseja remover esta ração de venda?')) return;
    this.racaoService.remover(id).subscribe({
      next: () => {
        this.loadItems();
        this.showToast('Ração de venda removida!');
      },
      error: () => this.error.set('Erro ao remover. Tente novamente.'),
    });
  }

  private showToast(msg: string) {
    this.toast.set(msg);
    setTimeout(() => this.toast.set(null), 3000);
  }
}
