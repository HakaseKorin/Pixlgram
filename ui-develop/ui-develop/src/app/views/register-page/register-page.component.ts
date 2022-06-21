import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { RegisterService } from 'src/app/services/register.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {
  user: User = {
    id: 0,
    username: '',
    profileImg: ''
  };

  username: string = "";
  password: string = "";

  areFieldsFilled: boolean = false;
  usernameError: boolean = false;
  passwordError: boolean = false;
  registerError: boolean = false;

  constructor(private registerService: RegisterService) { }

  ngOnInit(): void {
  }

  register() {
    this.checkRegister();
    /* commented out the lines below because they cause 
    the username and password validation error messages
    to appear even after entering the proper credentials */
    // this.username = "";
    // this.password = "";
    this.checkFields();  
  }

  checkFields() {
    this.checkUser();
    this.checkPass();
    this.areFieldsFilled = (this.usernameError === false && this.passwordError === false) ? true : false;
  }

  checkUser() {
    this.usernameError = this.username === "" ? true : false;
  }

  checkPass() {
    this.passwordError = this.password === "" ? true : false;
  }

  checkRegister() {
    this.registerService.register(this.username, this.password).subscribe( data => {
      document.cookie = 'authToken=' + JSON.stringify(data);
      this.sendToLandingPage();
    }, (err: HttpErrorResponse) => {
      this.registerError = true;
      return;
    });
  }

  sendToLandingPage(){
    window.location.href = "landing";
  }

}
