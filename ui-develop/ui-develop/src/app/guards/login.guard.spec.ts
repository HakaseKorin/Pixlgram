import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AuthService } from 'src/app/services/auth.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LandingPageComponent } from '../views/landing-page/landing-page.component';
import { RegisterPageComponent } from '../views/register-page/register-page.component';
import { LoginPageComponent } from '../views/login-page/login-page.component';


import { LoginGuard } from './login.guard';

describe('LoginGuard', () => {
  let guard: LoginGuard;
  let service: AuthService;
  let http: HttpClient;
  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([
      { path: 'landing', component: LandingPageComponent},
      { path: 'register', component: RegisterPageComponent},
      ])],
      providers: [AuthService]
    })
    .compileComponents();
    router = TestBed.inject(Router);
    service = TestBed.inject(AuthService);
    guard = TestBed.inject(LoginGuard);
  });

  beforeEach(() => {

  });

  afterEach(() => {
    
  })

  

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });

  it('return true on canActivate', () => {
    spyOn(service, 'IsLoggedIn').and.returnValue(false);
    expect(guard.canActivate()).toBeTruthy();
  });

  it('return false on canActivate', () => {
    spyOn(service, 'IsLoggedIn').and.returnValue(true);
    expect(guard.canActivate()).toBeFalsy();
  });


});
