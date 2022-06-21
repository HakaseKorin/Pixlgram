import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  @Input()
  post: Post = {
    id: 0,
    user: {
      id: 0,
      username: "JohnDoe",
      profileImg: ""
    },
    img: "img",
    description: "description",
    createdOn: new Date(),
    comments: {
      items: [],
      hasNext: false,
      totalElements: 0
    }
  }
  
  pageNumber: number = 1;
  lastPage: boolean = false;
  readonly pageSize: number = 5;
  commentDisplayButtonText: string = "View more comments";

  constructor(private commentService: CommentService) {}

  ngOnInit(): void {
  }

  loadComments() {
    this.pageNumber = (this.lastPage == true) ? 1 : this.pageNumber + 1;

    this.commentService.getCommentByPostId(this.post.id, this.pageNumber, this.pageSize).subscribe((data) => {
      this.lastPage = data.last;

      if(this.pageNumber == 1) this.post.comments.items = data.comments;
      else data.comments.forEach((element: Comment) => { this.post.comments.items.push(element); });

      this.commentDisplayButtonText = (this.lastPage == false) ? "View more comments" : "View less comments";
    });
  }
}