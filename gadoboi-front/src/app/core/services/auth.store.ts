import { Injectable, signal, computed } from '@angular/core';
import { AuthResponse } from '../models';

const TOKEN_KEY = 'gadoboi_token';

@Injectable({ providedIn: 'root' })
export class AuthStore {
  private readonly _token = signal<string | null>(localStorage.getItem(TOKEN_KEY));

  readonly token = this._token.asReadonly();
  readonly isAuthenticated = computed(() => !!this._token());

  setAuth(response: AuthResponse): void {
    localStorage.setItem(TOKEN_KEY, response.token);
    this._token.set(response.token);
  }

  clear(): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem('gadoboi_user');
    this._token.set(null);
  }
}
