import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthTokenService } from './auth-token.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  BASE_URL = 'http://localhost:8080';

  httpOptions = {
    'Content-Type': 'application/json'
  }

  constructor(private http: HttpClient, private authTokenService: AuthTokenService) { }

  authenticateUser(user) {
    return this.http.post(`${this.BASE_URL}/sellers/login`,user, this.httpOptions);
  }

  registerUser(user) {
    return this.http.post(`${this.BASE_URL}/sellers/register`,user,this.httpOptions);
  }

  getCurrentUser() {
    return this.http.get(`${this.BASE_URL}/sellers/user`,{
      headers: {
        'token': this.authTokenService.getToken(),
        'Content-Type': 'application/json'
      }
    })
  }

}
