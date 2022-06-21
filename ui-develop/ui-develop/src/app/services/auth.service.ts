import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  getLoginToken(username: string, password: string):Observable<any>{
    const formData = new FormData();
    formData.append("username", username);
    formData.append("password", password);
    return this.http.post<String>(`${environment.auth}/auth/login`, formData);
  }

  deleteLoginToken()
  {
    let authCookie = JSON.parse(JSON.stringify(this.cookies.get('authService')));
    console.log(authCookie)
    const formData = new FormData();
    formData.append("accessToken", authCookie.access_token);
    formData.append("refreshToken", authCookie.refresh_token);
    return this.http.post<String>(`${environment.auth}/auth/logout`, formData);
  }



  constructor(private http:HttpClient, private cookies: CookieService) { }

  IsLoggedIn(){
      return !!document.cookie;
  }

}
