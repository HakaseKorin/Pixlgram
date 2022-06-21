import { Component, Input, OnInit } from '@angular/core';
import { Post } from 'src/app/models/post';
import { CommentService } from 'src/app/services/comment.service';
import { PostService } from 'src/app/services/post.service';
import { Comment } from 'src/app/models/comment';




@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.css']
})

export class LandingPageComponent implements OnInit {
  constructor(private postService: PostService, private commentService: CommentService) { }

  scrollCount = 1;
  pageSize = 5;
  nextEmpty = false;

 
  


  async ngOnInit(): Promise<void> {
    
    

    this.postService.getPostByPageAndSize(this.scrollCount++, this.pageSize)
      .subscribe((data) => {
        data.forEach(post => {
          this.addPost(post);
        })
      })
  }

  @Input()
  posts: Post[] = []
  
  async onScroll() {
    if (!this.nextEmpty) {
      this.postService.getPostByPageAndSize(this.scrollCount++, this.pageSize)
        .subscribe((data) => {
          //This will loop through the new data and add it to the posts array.
            data.forEach(post => {
              this.addPost(post);
            });
        })
    }
  }


  addPost(post: Post){

    let tempPost: Post = {
      id: post.id,
      user: {
        id: post.user.id,
        username: post.user.username,
        profileImg: post.user.profileImg
      },
      img: post.img,
      description: post.description,
      createdOn: post.createdOn,
      comments: {
        items: [],
        hasNext: false,
        totalElements: 0
      }
    }

    this.commentService.getCommentByPostId(post.id, 1, this.pageSize).subscribe( (data) => {
      tempPost.comments.items = data.comments;
      tempPost.comments.hasNext = data.last;
      tempPost.comments.totalElements = data.totalElements;
  })

    this.posts.push(tempPost);
  };
}
