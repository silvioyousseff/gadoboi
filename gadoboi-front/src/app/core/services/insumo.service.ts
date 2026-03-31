import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Insumo } from '../models';

@Injectable({ providedIn: 'root' })
export class InsumoService extends ApiService {
  private readonly url = `${this.baseUrl}/api/v1/insumo`;

  listar(): Observable<Insumo[]> {
    return this.http.get<Insumo[]>(this.url);
  }

  criar(insumo: Partial<Insumo>): Observable<Insumo> {
    return this.http.post<Insumo>(this.url, insumo);
  }

  atualizar(id: number, insumo: Partial<Insumo>): Observable<Insumo> {
    return this.http.put<Insumo>(`${this.url}/${id}`, insumo);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
