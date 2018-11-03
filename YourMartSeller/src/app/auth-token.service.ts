import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthTokenService {

  constructor() { }

  saveToken(token) {
    window.localStorage.setItem('authToken',token);
  }

  getToken() {
    return window.localStorage.getItem('authToken');
  }

  deleteToken() {
    window.localStorage.removeItem('authToken');
  }
  
}
