import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Regiao } from '../models';

@Injectable({ providedIn: 'root' })
export class RegiaoService extends ApiService {
  private readonly url = `${this.baseUrl}/api/v1/regiao`;

  listar(): Observable<Regiao[]> {
    return this.http.get<Regiao[]>(this.url);
  }

  criar(regiao: Partial<Regiao>): Observable<Regiao> {
    return this.http.post<Regiao>(this.url, regiao);
  }

  atualizar(id: number, regiao: Partial<Regiao>): Observable<Regiao> {
    return this.http.put<Regiao>(`${this.url}/${id}`, regiao);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
