import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CookieService } from 'ngx-cookie-service';
import { NewPostService } from './new-post.service';
import { environment } from 'src/environments/environment';


describe('NewPostService', () => {
  let service: NewPostService;
  let httpMock: HttpTestingController;
  let injector: TestBed;

  beforeEach( async() => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CookieService]
    })
    injector = getTestBed();
    service = TestBed.inject(NewPostService);
    httpMock = injector.get(HttpTestingController);
    
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("should add a new post onto the post crud", () => {
      let tempFile = new File([""], "file");
      service.addNewPost(tempFile, "token:\"importantinfo\",nonusable", "descript", "user").subscribe();

      const req = httpMock.expectOne(`${environment.fems}/posts`);
      expect(req.request.method).toEqual('POST');
      });

      
  })

