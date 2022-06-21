import { HttpErrorResponse } from '@angular/common/http';
import { Component,NgModule,OnInit } from '@angular/core';
import { PostService } from './services/post.service';
import { Post } from './models/post';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  constructor(){}

}
