import { Component, OnInit } from '@angular/core';
import { Seller, SellerResponse } from '../models/sellerResponse';
import { AuthenticateService } from '../authenticate.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  user: Seller;

  constructor(private authenticateService: AuthenticateService) {
    this.user = {
      username: ''
    }
  }

  ngOnInit() {
    this.fetchCurrentUser()
  }

  fetchCurrentUser() {
    this.authenticateService.getCurrentUser().subscribe((data: SellerResponse) => {
      this.user = data.payload;
    })
  }

}
