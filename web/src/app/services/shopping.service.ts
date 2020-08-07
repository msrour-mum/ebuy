import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Cart} from '../models/shopping/cart';
import {CartManager} from '../models/shopping/cart-manager';

@Injectable({
  providedIn: 'root',
})
export class ShoppingService {

  private CART_STORAGE_NAME = '_cart';
  private cartManager: CartManager = null;
  public cart: Observable<Cart> = null;

  constructor(private http: HttpClient) {
    this.getCart().subscribe((data) => this.cartManager = new CartManager(data));
    this.cart = this.getCart().asObservable();
  }

  public addProduct(product: any, quantity: number): void {
    this.cartManager.add(product, quantity);
    this.setCart(this.cartManager.getCart());
  }

  public removeProduct(product: any, quantity: number): void {
    this.cartManager.remove(product, quantity);
    this.setCart(this.cartManager.getCart());
  }



  public getCart(): BehaviorSubject<Cart>  {
    if (localStorage.getItem(this.CART_STORAGE_NAME) != null ) {
      return new BehaviorSubject<Cart>(JSON.parse(localStorage.getItem(this.CART_STORAGE_NAME)));
    }
    const cart = new Cart();
    localStorage.setItem(this.CART_STORAGE_NAME, JSON.stringify(cart));
    return new BehaviorSubject<Cart>(cart);
  }

  private setCart(cart: Cart): void {
    localStorage.setItem(this.CART_STORAGE_NAME, JSON.stringify(cart));
  }
}
