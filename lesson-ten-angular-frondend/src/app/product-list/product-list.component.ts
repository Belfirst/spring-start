import { Component, OnInit } from '@angular/core';
import {ProductService} from "../model/product.service";
import {Product} from "../model/product";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent implements OnInit {

  public products: Product[] = [];
  public isError: boolean = false;

  constructor(public productService: ProductService) { }

  private retrieveProducts(){
    this.productService.findAll()
      .then(res => {
        this.isError = false;
        this.products = res;
      })
      .catch(err =>{
        this.isError = true;
        console.log(err);
      })
  }

  ngOnInit(): void {
   this.retrieveProducts();
  }

  delete(id: number) {
    this.productService.delete(id)
    .then(() => {
      this.retrieveProducts();
    })
  }
}
