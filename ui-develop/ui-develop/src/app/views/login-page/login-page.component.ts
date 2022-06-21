import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {


  user: User = {
    id: 0,
    username: '',
    profileImg: ''
  };

  username:string = "";
  password:string = "";

  testUser:string = "JohnDoe";
  testPass:string = "password";

  areFieldsFilled:boolean=false;
  usernameError:boolean=false;
  passwordError:boolean=false;
  loginError:boolean=false;

  constructor(private authService: AuthService) { }

  ngOnInit(): void {

  }

  login(){
    this.checkLogin();
    /* commented out the lines below because they cause 
    the username and password validation error messages
    to appear even after entering the proper credentials */
    // this.username = "";
    // this.password = "";
    this.checkFields();
  }
  
  checkFields(){
    this.checkUser();
    this.checkPass();

    this.areFieldsFilled = (this.usernameError === false && this.passwordError === false) ? true : false;
  }

  checkUser(){
    this.usernameError = this.username === "" ? true : false;
  }

  checkPass(){
    this.passwordError = this.password === "" ? true : false;
  }

  checkLogin(){
    //replace this with the authservice later
    // this.loginError = (this.username != this.testUser || this.password != this.testPass) ? true : false;
    // if(!this.loginError) window.location.href = "";//change pages to the landing page

    this.authService.getLoginToken(this.username, this.password).subscribe( data => {
      document.cookie = 'authToken=' + JSON.stringify(data);
      this.sendToLandingPage();
    },(err:HttpErrorResponse) => {
      this.loginError = true;
      return;
    });


  }

  sendToLandingPage(){
    window.location.href = "landing";
  }

  // register(){
  //   implemented in a different story
  // }

}
