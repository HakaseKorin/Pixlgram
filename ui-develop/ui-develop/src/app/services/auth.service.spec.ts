import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

describe('AuthService', () => {
  let service: AuthService;
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let username: "bob";
  let password: "marley";

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [AuthService]
    });
    injector = getTestBed();
    service = TestBed.inject(AuthService);
    httpMock = injector.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an Observable<String>', ()=> {
    const dummyToken = "AOAMSCIASMLCIASMCOLIML";
    service.getLoginToken(username, password).subscribe( token => {
      expect(token).toBeTruthy;
      expect(token).toEqual(dummyToken);
    })

    const request = httpMock.expectOne(`${environment.auth}/auth/login`);
    expect(request.request.method).toBe("POST");
    request.flush(dummyToken);
  });
});
