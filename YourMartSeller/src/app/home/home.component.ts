import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router } from "@angular/router";

import { AuthenticateService } from '../authenticate.service';
import { ProductService } from '../product.service';
import { Seller, SellerResponse } from '../models/sellerResponse';
import { Product, ProductListResponse } from '../models/productResponse';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  user: Seller;
  products: [Product];
  isLoading: boolean;
  count: number;

  sortAndFilter = new FormGroup({
    sortBy: new FormControl(''),
    review: new FormControl(''),
    needApproval: new FormControl(''),
    rejected: new FormControl(''),
    approved: new FormControl('')
  })

  constructor(private authenticateService: AuthenticateService, private productService: ProductService, private route: Router) {
    this.isLoading = true;
    this.count = 0;
  }

  ngOnInit() {
    this.fetchCurrentUser()
  }

  fetchCurrentUser() {
    this.authenticateService.getCurrentUser().subscribe((data: SellerResponse) => {
      this.user = data.payload;
      this.fetchAllProducts("");
    })
  }

  fetchAllProducts(query) {
    this.isLoading = true;
    this.productService.getAllProductsOfSeller(this.user.sellerId,query).subscribe((data: ProductListResponse) => {
      this.products = data.payload;
    },err => {

    }, () => {
      this.isLoading = false;
    })
  }

  onSortAndFilter() {
    let queryString = "?";
    let count = 0;
    let { sortBy, needApproval, rejected, approved, review } = this.sortAndFilter.value;

    if(sortBy!=""){
      queryString += `sortBy=${sortBy}`
      count++;
    }

    if(needApproval){
      if(count>0) {
        queryString += "&"
      }
      queryString += `status=NEED_APPROVAL`
      count++;
    }

    if(rejected) {
      if(count>0) {
        queryString += "&"
      }
      queryString += `status=REJECTED`
      count++;
    }

    if(approved){
      if(count>0) {
        queryString += "&"
      }
      queryString += `status=APPROVED`
      count++;
    }

    if(review) {
      if(count>0) {
        queryString += "&"
      }
      queryString += `status=REVIEW`
    }

    this.fetchAllProducts(queryString);
  }

  addProduct() {
    console.log("add")
    this.route.navigate(['/create'])
  }

  onScroll() {
    this.count++;
    if(this.count<1) {
      this.productService.getAllProductsOfSeller(this.user.sellerId,"?offset="+this.count).subscribe((data: ProductListResponse) => {
        this.products = [...this.products, data.payload];
      },err => {

      }, () => {
        this.isLoading = false;
      })
    }
  }
}
