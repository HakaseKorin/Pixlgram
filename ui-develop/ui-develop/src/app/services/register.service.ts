import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private http: HttpClient) { }

  register(username:string, password:string):Observable<any> {
    const formData = new FormData();
    formData.append("username", username);
    formData.append("password", password);
    return this.http.post(`${environment.auth}/auth/register`, formData);
  }
}
