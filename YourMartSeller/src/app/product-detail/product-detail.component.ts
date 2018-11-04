import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from "@angular/router";

import { ProductService } from '../product.service';
import { Product, ProductResponse } from '../models/productResponse';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {

  productId: number;
  product: Product;
  isLoading: bolean;

  constructor(private active: ActivatedRoute, private productService: ProductService, private route: Router) {
    this.active.params.subscribe( params => {
        this.productId = params.id
    });
    this.isLoading = true;
  }

  ngOnInit() {
    this.fetchProduct();
  }

  fetchProduct() {
    this.productService.getProduct(this.productId).subscribe((data: ProductResponse) => {
      this.product = data.payload;
      this.isLoading = false;
    })
  }

  onEdit() {
    this.route.navigate([`/edit/${this.productId}`])
  }

}
