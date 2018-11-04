import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  BASE_URL = 'http://localhost:8080';

  httpOptions = {
    'Content-Type': 'application/json'
  }

  constructor(private http: HttpClient) { }

  getAllProductsOfSeller(sellerId, queryString) {
    return this.http.get(`${this.BASE_URL}/products/seller/${sellerId}${queryString}`);
  }

  getProduct(productId) {
    return this.http.get(`${this.BASE_URL}/products/${productId}`);
  }

  addProduct(product) {
    return this.http.post(`${this.BASE_URL}/products`,product,this.httpOptions);
  }
}
