import { Injectable } from '@angular/core';
import { HttpClient } from  '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Post } from '../models/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  getPostByPageAndSize(pageNumber:number, pageSize:number):Observable<Post[]>{
    return this.http.get<Post[]>(`${environment.fems}/posts?pageNumber=${pageNumber}&pageSize=${pageSize}`)
  }
  /*Before injecting the HttpClient as dependency and trying to use it,we need to import the Angular
  'HttpClientModule' in the app.module.ts* so it can be available everywhere in our application*/
  constructor(private http:HttpClient) { }
}
