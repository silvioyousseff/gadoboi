import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';
import { AuthRequest, AuthResponse } from '../models';

export interface CadastroRequest {
  nome: string;
  sobrenome: string;
  email: string;
  senha: string;
  cpf: string;
  telefone?: string;
  celular?: string;
}

@Injectable({ providedIn: 'root' })
export class AuthService extends ApiService {
  login(req: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/api/v1/auth/login`, req);
  }

  cadastro(req: CadastroRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.baseUrl}/api/v1/auth/cadastro`, req);
  }
}
