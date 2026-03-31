import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Racao } from '../models';

@Injectable({ providedIn: 'root' })
export class RacaoService extends ApiService {
  private readonly url = `${this.baseUrl}/api/v1/racao`;

  listar(): Observable<Racao[]> {
    return this.http.get<Racao[]>(this.url);
  }

  listarVenda(): Observable<Racao[]> {
    return this.http.get<Racao[]>(`${this.url}?status=V`);
  }

  buscar(id: number): Observable<Racao> {
    return this.http.get<Racao>(`${this.url}/${id}`);
  }

  criar(racao: Partial<Racao>): Observable<Racao> {
    return this.http.post<Racao>(this.url, racao);
  }

  atualizar(id: number, racao: Partial<Racao>): Observable<Racao> {
    return this.http.put<Racao>(`${this.url}/${id}`, racao);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
