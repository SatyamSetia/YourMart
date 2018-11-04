import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';

import { ProductService } from '../product.service';
import { AuthenticateService } from '../authenticate.service';
import { Seller, SellerResponse } from '../models/sellerResponse';
import { Product, ProductResponse } from '../models/productResponse';

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
  isEditMode: boolean;
  product: Product;

  constructor(private productService: ProductService, private authenticateService: AuthenticateService, private active: ActivatedRoute, private route: Router) { }

  ngOnInit() {
    this.fetchCurrentUser();
    this.isEditMode = this.active.url.value.length == 2 ? true: false;

    if(this.isEditMode) {
      this.fetchProduct();
    }
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

  fetchProduct() {
    this.active.params.subscribe( params => {
      this.productService.getProduct(params.id).subscribe((data: ProductResponse) => {
        this.product = data.payload;
        this.populateProduct();
      })
    });
  }

  populateProduct() {
    this.productForm.setValue({
      name: this.product.name,
      prodAttr: this.product.prodAttr,
      sellerProdCode: this.product.sellerProdCode,
      shortDesc: this.product.shortDesc,
      longDesc: this.product.longDesc,
      dimensions: this.product.dimensions,
      categories: '',
      mrp: this.product.mrp,
      ssp: this.product.ssp,
      ymp: this.product.ymp,
      primaryImage: this.product.primaryImage,
      gallery: ''
    })
  }

  onSubmit() {
    if(this.isEditMode) {
      this.editProduct();
    } else {
      this.createNewProduct();
    }
    this.route.navigate(['/home'])
  }

  editProduct() {
    let inputs = this.productForm.value;

    this.productService.editProduct({
      productId: this.product.productId,
      dimensions: inputs.dimensions,
      longDesc: inputs.longDesc,
      mrp: inputs.mrp,
      name: inputs.name,
      primaryImage: inputs.primaryImage,
      prodAttr: inputs.prodAttr,
      sellerProdCode: inputs.sellerProdCode,
      ssp: inputs.ssp,
      ymp: inputs.ymp,
      shortDesc: inputs.shortDesc
    }).subscribe(data => {
      console.log(data);
    })
  }

  createNewProduct() {
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
