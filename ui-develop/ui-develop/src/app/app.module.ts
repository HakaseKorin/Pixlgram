import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { PostComponent } from './components/post/post.component';

import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { PostService } from './services/post.service';
import { NavbarComponent } from './views/navbar/navbar.component';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { RegisterPageComponent } from './views/register-page/register-page.component';
import { CookieService } from 'ngx-cookie-service';

@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    routingComponents,
    NavbarComponent,
    RegisterPageComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatIconModule,
    InfiniteScrollModule,
    FormsModule,
    AppRoutingModule,
    RouterModule,
  ],
  providers: [
    PostService,
    CookieService
  ],
  bootstrap: [AppComponent],
})
export class AppModule { }
