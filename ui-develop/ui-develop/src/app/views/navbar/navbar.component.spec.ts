import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { LandingPageComponent } from '../landing-page/landing-page.component';
import { LoginPageComponent } from '../login-page/login-page.component';
import { NavbarComponent } from './navbar.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('NavbarComponent', () => {
  let component: NavbarComponent;
  let fixture: ComponentFixture<NavbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [NavbarComponent],
      imports: [RouterTestingModule, HttpClientTestingModule]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call ngOnInit', () => {
    let navbarCom = fixture.debugElement.injector.get(NavbarComponent);
    let navbarCopy = spyOn(navbarCom, 'ngOnInit').and.callThrough();
    component.ngOnInit();
    expect(navbarCopy).toHaveBeenCalled();

  });
});
