import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { getTestBed, TestBed } from '@angular/core/testing';

import { RegisterService } from './register.service';
import { environment } from 'src/environments/environment';

describe('RegisterService', () => {
  let service: RegisterService;
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let username: "username";
  let password: "password";

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [RegisterService]
    });
    injector = getTestBed();
    service = TestBed.inject(RegisterService);
    httpMock = injector.get(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an Observable<String>', ()=> {
    const dummyToken = "AOAMSCIASMLCIASMCOLIML";
    service.register(username, password).subscribe( token => {
      expect(token).toBeTruthy;
      expect(token).toEqual(dummyToken);
    })

    const request = httpMock.expectOne(`${environment.auth}/auth/register`);
    expect(request.request.method).toBe("POST");
    request.flush(dummyToken);
  });
});
