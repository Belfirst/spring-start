import { Injectable } from '@angular/core';
import {Product} from "./product";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private identity: number = 6;

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  // private products: { [key: number]: Product } = {
  //   1: new Product(1, 'First product', 10),
  //   2: new Product(2, 'Second product', 50),
  //   3: new Product(3, 'Third product', 20),
  //   4: new Product(4, 'Fourth product', 40),
  //   5: new Product(5, 'Fifth product', 30),
  //
  //
  // }

  constructor(public http: HttpClient) { }

  public findAll(){
    return this.http.get<Product[]>('/api/v1/product/all').toPromise();
    // return new Promise<Product[]>((resolve, reject) =>
    // {
    //   resolve(
    //     Object.values(this.products)
    //   )
    // })
  }

  public findById(id: number) {
    return this.http.get<Product>(`/api/v1/product/${id}`).toPromise();
    // return new Promise<Product>((resolve, reject) => {
    //   resolve(
    //     this.products[id]
    //   )
    // })
  }

  public update(product: Product) {
    return this.http.put<Product>('/api/v1/product', product, this.httpOptions).toPromise();
  }

  public save(product: Product) {
    return this.http.post<Product>('/api/v1/product', product, this.httpOptions).toPromise();
    // return new Promise<void>((resolve, reject) => {
    //   if (product.id == -1) {
    //     product.id = this.identity++;
    //   }
    //   this.products[product.id] = product;
    //   resolve();
    // })
  }

  public delete(id: number) {
    return this.http.delete<Product>(`/api/v1/product/${id}`).toPromise();
    // return new Promise<void>((resolve, reject) => {
    //   delete this.products[id]
    //   resolve();
    // })
  }
}
