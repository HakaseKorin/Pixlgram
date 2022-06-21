import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PostComponent } from './post.component';
import { Comment } from 'src/app/models/comment';
import { HttpClient, HttpHandler } from '@angular/common/http';
import { CommentService } from 'src/app/services/comment.service';
import { of } from 'rxjs';

describe('PostComponent', () => {
  let component: PostComponent;
  let commentService: CommentService;
  let fixture: ComponentFixture<PostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostComponent ],
      providers: [HttpClient, HttpHandler]
    })
    .compileComponents();
    commentService = TestBed.inject(CommentService);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('one post should be created by default', ()=> {
    expect(component.post).toBeTruthy();
  });

  it('loadComments should return an array of comments', ()=> {
    let comment: Comment =
      {
      id: 0,
      postId: 0,
      username: "JaneDoe",
      body: "body",
      createdOn: new Date(),
    };

    spyOn(commentService, 'getCommentByPostId').and.returnValue(of(comment));
    component.loadComments();
    expect(component.post.comments.totalElements).toBe(0);
  });
});