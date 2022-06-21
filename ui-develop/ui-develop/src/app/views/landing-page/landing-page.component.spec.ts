import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, fakeAsync, getTestBed, TestBed, tick } from '@angular/core/testing';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { PostService } from 'src/app/services/post.service';
import { LandingPageComponent } from './landing-page.component';
import { of } from 'rxjs';
import { CommentService } from 'src/app/services/comment.service';
import { environment } from 'src/environments/environment';
import { RouterTestingModule } from '@angular/router/testing';


describe('LandingPageComponent', () => {
  let component: LandingPageComponent;
  let fixture: ComponentFixture<LandingPageComponent>;
  let injector: TestBed;
  let service: PostService;
  let commentService: CommentService;
  let httpMock: HttpTestingController;
  let today = new Date();
  const dummyPosts: Post[] = [
    {
      id: 0,
      user: {
        id: 0,
        username: "JohnDoe",
        profileImg: "user image path"
      },
      img: "post image path",
      description: "description",
      createdOn: today,
      comments: {
        items: [{
          id: 0,
          postId: 0,
          username: "JohnDoe",
          body: "body",
          createdOn: today
        }],
        hasNext: false,
        totalElements: 1
      }
    },
    {
      id: 1,
      user: {
        id: 1,
        username: "JaneDoe",
        profileImg: "user image path"
      },
      img: "post image path",
      description: "description",
      createdOn: today,
      comments: {
        items: [{
          id: 1,
          postId: 1,
          username: "JaneDoe",
          body: "body",
          createdOn: today
        }],
        hasNext: false,
        totalElements: 1
      }
    }
  ];
  const dummyComments: Comment[] = [
    {
      id: 0,
      postId: 0,
      username: "Bruce Lee",
      body: "I can kick faster than you!",
      createdOn: today
    },
    {
      id: 1,
      postId: 0,
      username: "Chuck Norris",
      body: "I can punch faster than you!",
      createdOn: today
    }
  ];

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, InfiniteScrollModule, RouterTestingModule],
      providers: [PostService, CommentService],
      declarations: [ LandingPageComponent ]
    })
    .compileComponents();
    injector = getTestBed();
    service = TestBed.inject(PostService);
    commentService = TestBed.inject(CommentService);
    httpMock = injector.get(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LandingPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('scrollCount should increase after onScroll()', ()=>{
    let scrollCountAfter = component.scrollCount + 1;
    component.onScroll();
    expect(component.scrollCount).toBe(scrollCountAfter);
  });

  it('add Post should add an additional post', () => {
    const post: Post = {
      id: 0,
      user: {
        id: 0,
        username: "JohnDoe",
        profileImg: "profileImg"
      },
      img: "post.img",
      description:" post.description",
      createdOn: new Date,
      comments: {
        items: [{
          id: 0,
          postId: 0,
          username: "JaneDoe",
          body: "body",
          createdOn: new Date(),
        }],
        hasNext: false,
        totalElements: 1
      }
    }
    component.addPost(post);

    expect(component.posts.length).toBeGreaterThan(0);

    expect(component.addPost).toBeTruthy;
  })

  it("should add each post on initialize", ()=> {
    spyOn(commentService, "getCommentByPostId").and.returnValue(of(dummyComments));
    spyOn(service, "getPostByPageAndSize").and.returnValue(of(dummyPosts));
    let spy = spyOn(component, "addPost");

    component.ngOnInit();

    expect(component.addPost).toHaveBeenCalled();
  })

  it("should add each post on onScroll", ()=> {
    spyOn(service, "getPostByPageAndSize").and.returnValue(of(dummyPosts));
    let spy = spyOn(component, "addPost");

    component.onScroll();

    expect(component.addPost).toHaveBeenCalled();
  })

});
