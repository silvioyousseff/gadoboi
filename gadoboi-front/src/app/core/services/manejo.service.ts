import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Manejo } from '../models';

@Injectable({ providedIn: 'root' })
export class ManejoService extends ApiService {
  private readonly url = `${this.baseUrl}/api/v1/manejo`;

  listar(): Observable<Manejo[]> {
    return this.http.get<Manejo[]>(this.url);
  }

  criar(manejo: Partial<Manejo>): Observable<Manejo> {
    return this.http.post<Manejo>(this.url, manejo);
  }

  atualizar(id: number, manejo: Partial<Manejo>): Observable<Manejo> {
    return this.http.put<Manejo>(`${this.url}/${id}`, manejo);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
