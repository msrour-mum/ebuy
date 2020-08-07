import {OrderItem} from './orderItem';
import {Product} from './product';

export class Cart {

  public subTotal: number;
  public tax: number;
  public shipping: number;
  public total: number;
  public items: OrderItem[];
  public count: number;

  constructor() {
    this.tax = 0;
    this.shipping = 2.0;
    this.items = [];
  }
}
