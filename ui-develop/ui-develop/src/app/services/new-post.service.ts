import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CookieService } from 'ngx-cookie-service';
import { Post } from '../models/post';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class NewPostService {

  // when putting in the string for accessToken just use the CookieService and use CookieService.get("authCookie")
  addNewPost(file: File, accessToken: string, description: string, username: string):Observable<any>{
    let authCookie = accessToken.split(":\"")[1].split("\",")[0];

    const headerRequest ={
      headers: new HttpHeaders().append("access_token", authCookie)
    };

    const formData = new FormData();
    formData.append("image", file);
    formData.append("description", description);
    formData.append("userId", username);
    return this.http.post<Post>(`${environment.fems}/posts`, formData, headerRequest);
  }

  constructor(private http:HttpClient, private cookies: CookieService) { }

  IsLoggedIn(){
    return !!document.cookie;
  }
}
