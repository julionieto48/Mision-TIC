import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  token(arg0: string, token: any) {
    throw new Error('Method not implemented.');
  }
  setToken(token: any) {
    throw new Error('Method not implemented.');
  }

  constructor(private http: HttpClient) { }

  // aca hago la peticion
  signin(body: any){ // {username,password}

    return this.http.post('http://localhost:3000/auth/signin',body).toPromise();


  }
}
