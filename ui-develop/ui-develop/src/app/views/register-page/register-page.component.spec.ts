import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, getTestBed, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { RegisterService } from 'src/app/services/register.service';
import { environment } from 'src/environments/environment';
import { RegisterPageComponent } from './register-page.component';

describe('RegisterPageComponent', () => {
  let component: RegisterPageComponent;
  let fixture: ComponentFixture<RegisterPageComponent>;
  let injector: TestBed;
  let service: RegisterService;
  let httpMock: HttpTestingController;
  let spy: any;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [RegisterService],
      declarations: [ RegisterPageComponent ]
    })
    .compileComponents();
    injector = getTestBed();
    service = TestBed.inject(RegisterService);
    httpMock = injector.get(HttpTestingController);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    spyOn(component, 'sendToLandingPage');
  });

  afterEach(() => {
    spy = '';
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('register should check for empty fields', () => {
    component.username = '';
    component.password = '';
    component.checkFields();
    expect(component.usernameError).toBeTruthy();
    expect(component.passwordError).toBeTruthy();
    expect(component.areFieldsFilled).toBeFalsy();
  });

  it('register should handle non-empty fields', () => {
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
  // it('register should clear user and pass variables', () => {
  //   component.username = "username";
  //   component.password = "password";   
  //   component.register();
  //   expect(component.username).toBeFalsy();
  //   expect(component.password).toBeFalsy();
  // });
  
  it('when register is correct loginError should be false', () => {
    component.username = "JohnDoe";
    component.password = "password";
    component.register();
    expect(component.registerError).toBeFalsy();
  });

  it('when register is correct the access token should be set', ()=> {
    spy = spyOn(service, 'register').and.returnValue(of("ABCDEFG"));
    component.checkRegister();
    expect(document.cookie).toBe('authToken=\"ABCDEFG\"');
  });

  it('when register is incorrect, the registerError should set to be true', ()=> {
    component.checkRegister();
    const request = httpMock.expectOne(`${environment.auth}/auth/register`);
    request.flush('', {status: 404, statusText: 'User could not be found'});
    expect(component.registerError).toBeTrue;
  });
});
