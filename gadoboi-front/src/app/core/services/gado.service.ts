import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Gado } from '../models';

@Injectable({ providedIn: 'root' })
export class GadoService extends ApiService {
  private readonly url = `${this.baseUrl}/api/v1/gado`;

  listar(): Observable<Gado[]> {
    return this.http.get<Gado[]>(this.url);
  }

  buscar(id: number): Observable<Gado> {
    return this.http.get<Gado>(`${this.url}/${id}`);
  }

  criar(gado: Gado): Observable<Gado> {
    return this.http.post<Gado>(this.url, gado);
  }

  atualizar(id: number, gado: Gado): Observable<Gado> {
    return this.http.put<Gado>(`${this.url}/${id}`, gado);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }
}
