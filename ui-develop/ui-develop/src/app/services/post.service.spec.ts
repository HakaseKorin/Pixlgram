import { getTestBed, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PostService } from './post.service';
import { Post } from '../models/post';
import { environment } from 'src/environments/environment';

describe('PostService', () => {
  let injector: TestBed;
  let service: PostService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PostService]
    });
    injector = getTestBed();
    service = TestBed.inject(PostService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach( () => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getPostsByPageAndSize should return an Observable<Page[]>', () => {
    let today = new Date();
    const dummyPosts: Post[]= [
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

    service.getPostByPageAndSize(1,2).subscribe(posts=>{
      expect(posts.length).toBe(2);
      expect(posts).toEqual(dummyPosts);
    });

    const request = httpMock.expectOne(`${environment.fems}/posts?pageNumber=1&pageSize=2`);
    expect(request.request.method).toBe('GET');
    request.flush(dummyPosts);
    
  });

});
