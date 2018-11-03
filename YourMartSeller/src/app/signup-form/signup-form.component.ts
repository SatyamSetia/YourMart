import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { AuthenticateService } from '../authenticate.service';
import { LoginSignupResponse } from '../models/loginSignupResponse';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['./signup-form.component.css']
})
export class SignupFormComponent implements OnInit {

  error: String;

  signupForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl(''),
    company: new FormControl(''),
    ownerName: new FormControl(''),
    address: new FormControl(''),
    email: new FormControl(''),
    gstNumber: new FormControl(''),
    telephone: new FormControl('')
  })

  constructor(private authenticateService: AuthenticateService) {
    this.error = null;
  }

  ngOnInit() {
  }

  onSubmit() {
    this.authenticateService.registerUser(this.signupForm.value).subscribe((data: LoginSignupResponse) => {
      console.log(data);
      if(data.status != 200) {
        this.error = data.error;
      } else {
        this.error = null;
      }
    })
  }

}
