import { getTestBed, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CommentService } from './comment.service';
import { Comment } from '../models/comment';
import { environment } from 'src/environments/environment';

describe('CommentService', () => {
  let service: CommentService;
  let injector: TestBed;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CommentService]
    });
    injector = getTestBed();
    service = TestBed.inject(CommentService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach( () => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  
  it('getCommentByPostId should return an Observable<Comment[]>', () => {
    let today = new Date();
    const dummyComments: Comment[]= [
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
      // {
      //   id: 2,
      //   postId: 0,
      //   username: "Mike Tyson",
      //   body: "I'm gonna bite your ear!",
      //   createdOn: today
      // }
    ];

    service.getCommentByPostId(0, 1, 2).subscribe(comments => {
      expect(comments.length).toBe(2);
      expect(comments).toEqual(dummyComments);
    });

    const request = httpMock.expectOne(`${environment.fems}/posts/0/comments?pageNumber=1&pageSize=2`);
    expect(request.request.method).toBe('GET');
    request.flush(dummyComments);

  });


});

