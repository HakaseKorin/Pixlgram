import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed, getTestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import { LoginPageComponent } from './login-page.component';


describe('LoginPageComponent', () => {
  let component: LoginPageComponent;
  let fixture: ComponentFixture<LoginPageComponent>;
  let injector: TestBed;
  let service: AuthService;
  let httpMock: HttpTestingController;
  let spy: any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [AuthService],
      declarations: [ LoginPageComponent ]
    })
    .compileComponents();
    injector = getTestBed();
    service = TestBed.inject(AuthService);
    httpMock = injector.get(HttpTestingController);
  });


  beforeEach(() => {
    fixture = TestBed.createComponent(LoginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    spyOn(component, "sendToLandingPage");
  });

  afterEach(() => {
    spy = "";
  })

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Login should check for empty fields', () => {
    component.username = "";
    component.password = "";
    component.checkFields();

    expect(component.usernameError).toBeTruthy();
    expect(component.passwordError).toBeTruthy();
    expect(component.areFieldsFilled).toBeFalsy();
  });

  it('Login should handle non-empty fields', () => {
    component.username = "username";
    component.password = "password";   
    component.checkFields();

    expect(component.usernameError).toBeFalsy();
    expect(component.passwordError).toBeFalsy();
    expect(component.areFieldsFilled).toBeTruthy();
  });

  /* commented out the lines below because they cause 
  the username and password validation error messages
  to appear even after entering the proper credentials */
  // it('Login should clear user and pass variables', () => {
  //   component.username = "username";
  //   component.password = "password";   
  //   component.login();

  //   expect(component.username).toBeFalsy();
  //   expect(component.password).toBeFalsy();
  // });

  it('when login is correct loginError should be false', () => {
    component.username = "JohnDoe";
    component.password = "password";
    component.login();

    expect(component.loginError).toBeFalsy();
  });

  it('when login is correct the authToken should be set', ()=> {
    spy = spyOn(service, "getLoginToken").and.returnValue(of("ABCDEFG"));
    component.checkLogin();
    expect(document.cookie).toBe("authToken=\"ABCDEFG\"");
  })

  it('when login is incorrect, the loginError should set to be true', ()=> {
    component.checkLogin();

    const request = httpMock.expectOne(`${environment.auth}/auth/login`);
    request.flush("", {status: 404, statusText: "User could not be found"});

    expect(component.loginError).toBeTrue;

  })

});
