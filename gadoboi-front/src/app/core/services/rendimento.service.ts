import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { Rendimento, RendimentoRequest } from '../models';

@Injectable({ providedIn: 'root' })
export class RendimentoService extends ApiService {
  private readonly url = `${this.baseUrl}/api/v1/rendimento`;

  calcular(req: RendimentoRequest): Observable<Rendimento> {
    return this.http.post<Rendimento>(`${this.url}/calcular`, req);
  }
}
