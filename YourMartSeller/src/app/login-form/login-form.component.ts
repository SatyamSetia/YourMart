import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from "@angular/router";

import { AuthenticateService } from '../authenticate.service';
import { LoginSignupResponse } from '../models/loginSignupResponse';
import { AuthTokenService } from '../auth-token.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  error: String;

  loginForm = new FormGroup({
    username: new  FormControl(''),
    password: new FormControl('')
  })

  constructor(private authenticateService: AuthenticateService, private route: Router, private authTokenService: AuthTokenService) {
    this.error = null;
  }

  ngOnInit() {
  }

  onSubmit() {
    if(grecaptcha.getResponse() == "") {
        return false
    }
    this.authenticateService.authenticateUser(this.loginForm.value).subscribe((data: LoginSignupResponse) => {
      console.log(data);
      if(data.status != 200) {
        this.error = data.error;
      } else {
        this.error = null;
        this.authTokenService.saveToken(data.payload.token);
        this.route.navigate(['/home']);
      }
    },(err) => {
      console.log(err)
    },() => {
    })
  }

}
