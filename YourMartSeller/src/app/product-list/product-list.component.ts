import { Component, OnInit, Input} from '@angular/core';
import { ProductList } from '../models/productResponse';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  @Input() products: ProductList;

  constructor() { }

  ngOnInit() {
  }

}
