import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Cart} from '../../../models/shopping/cart';
import {ShoppingService} from '../../../services/shopping.service';
import {AppConfig} from '../../../config/app.config';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styles: [],
})
export class CartComponent implements OnInit {

  //public cart: Cart;
  public hostUrl: string = AppConfig.settings.apiServiceUrl;
  constructor(public shoppingService: ShoppingService,
              private router: Router) { }

  public ngOnInit(): void {
    //this.shoppingService.getCart().subscribe((data) => this.cart = data);
  }

  public addToCart(product): void {
    this.shoppingService.addProduct(product, 1);
  }

  public removeFromCartCart(product): void  {
    this.shoppingService.removeProduct(product, 1);
  }

}
