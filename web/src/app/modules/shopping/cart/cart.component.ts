import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styles: [
  ]
})
export class CartComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  public addProduct(product) {
    alert(product);
  }

  public removeProduct(product) {
    alert(product);
  }

}
