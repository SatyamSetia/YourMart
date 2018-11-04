import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

import { ProductService } from '../product.service';
import { AuthenticateService } from '../authenticate.service';
import { Seller, SellerResponse } from '../models/sellerResponse';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {

  productForm = new FormGroup({
    name: new FormControl(''),
    prodAttr: new FormControl(''),
    sellerProdCode: new FormControl(''),
    shortDesc: new FormControl(''),
    longDesc: new FormControl(''),
    dimensions: new FormControl(''),
    categories: new FormControl(''),
    mrp: new FormControl(''),
    ssp: new FormControl(''),
    ymp: new FormControl(''),
    primaryImage: new FormControl(''),
    gallery: new FormControl('')
  })

  user: Seller;
  images= new Array<String>();

  constructor(private productService: ProductService, private authenticateService: AuthenticateService) { }

  ngOnInit() {
    this.fetchCurrentUser();
  }

  addImage(image) {
    this.images.push(image);
    this.productForm.controls['gallery'].setValue("");
  }

  fetchCurrentUser() {
    this.authenticateService.getCurrentUser().subscribe((data: SellerResponse) => {
      this.user = data.payload;
    })
  }

  onSubmit() {
    console.log(this.productForm.value)
    let inputs = this.productForm.value;
    this.productService.addProduct({
      name: inputs.name,
      prodAttr: inputs.prodAttr,
      sellerId: this.user.sellerId,
      comments: inputs.comments,
      dimensions: inputs.dimensions,
      longDesc: inputs.longDesc,
      shortDesc: inputs.shortDesc,
      sellerProdCode: inputs.sellerProdCode,
      mrp: inputs.mrp,
      ymp: inputs.ymp,
      ssp: inputs.ssp,
      primaryImage: inputs.primaryImage,
      usageInstructions: inputs.usageInstructions,
      gallery: this.images
    }).subscribe(data => {
      console.log(data);
    })
  }

}
