import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { getTestBed, TestBed } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { PostService } from './services/post.service';

describe('AppComponent', () => {
  let injector: TestBed;
  let service: PostService;
  let httpMock: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PostService],
      declarations: [
        AppComponent
      ],
    }).compileComponents();
    injector = getTestBed();
    service = TestBed.inject(PostService);
    httpMock = injector.get(HttpTestingController);
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
