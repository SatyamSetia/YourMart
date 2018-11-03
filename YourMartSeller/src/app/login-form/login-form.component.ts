import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { AuthenticateService } from '../authenticate.service';
import { LoginSignupResponse } from '../models/loginSignupResponse';

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

  constructor(private authenticateService: AuthenticateService) {
    this.error = null;
  }

  ngOnInit() {
  }

  onSubmit() {
    this.authenticateService.authenticateUser(this.loginForm.value).subscribe((data: LoginSignupResponse) => {
      console.log(data);
      if(data.status != 200) {
        this.error = data.error;
      }
    })
  }

}
