import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Cart} from '../models/shopping/cart';
import {CartManager} from '../models/shopping/cart-manager';
import {AppConfig} from '../config/app.config';

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

  public clearCart(): void {
   this.getCart().subscribe((data) => {
      data.items.forEach((e) => {
        this.removeProduct(e.product, e.quantity);
      });
    });
   localStorage.removeItem(this.CART_STORAGE_NAME);
  }

  public checkout(body: any) {
    return this.http.post<any>(`${AppConfig.settings.apiServiceUrl}/checkout`, body);
  }

  private setCart(cart: Cart): void {
    localStorage.setItem(this.CART_STORAGE_NAME, JSON.stringify(cart));
  }



  public reportProfit(vendorId : number) {
    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/reports/${vendorId}/profits`);
  }
  public reportMyOrders(userId : number) {
    return this.http.get<any>(`${AppConfig.settings.apiServiceUrl}/reports/${userId}/orders`);
  }

}
