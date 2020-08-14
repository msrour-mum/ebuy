import {Product} from './product';

export class OrderItem {

  public product: Product;
  public quantity: number;
  public total: number;

  constructor(product: Product, quantity: number) {
    this.product = product;
    this.quantity = quantity;
  }

}
