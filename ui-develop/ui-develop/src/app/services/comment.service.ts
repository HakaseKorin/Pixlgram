import { Injectable } from '@angular/core';
import { HttpClient } from  '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  getCommentByPostId(postId:number, pageNumber:number, pageSize:number):Observable<any> {
    return this.http.get<any>(`${environment.fems}/posts/${postId}/comments?pageNumber=${pageNumber}&pageSize=${pageSize}`);
  }

  constructor(private http:HttpClient) { }
}
