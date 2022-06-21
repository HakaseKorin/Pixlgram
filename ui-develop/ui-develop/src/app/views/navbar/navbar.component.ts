import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { CookieService } from 'ngx-cookie-service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})

export class NavbarComponent implements OnInit {

  hideElement: boolean = false;

  constructor(public router: Router, private cookies: CookieService, private authService: AuthService) {
  }

  ngOnInit(): void {

  }

  cleanAuthToken()
  {
    this.authService.deleteLoginToken();
    this.cookies.delete('authToken');
  }

  checkCookie()
  {
      var match = document.cookie.match(RegExp('(?:^|;\\s*)' + 'authToken' + '=([^;]*)')); 
      return match ? match[1] : null;
  }

}
