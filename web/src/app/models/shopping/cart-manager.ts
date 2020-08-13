import {Cart} from './cart';
import {OrderItem} from './orderItem';
import {Product} from './product';

export class CartManager {

  private readonly cart: Cart;

  constructor(cart: Cart) {
    this.cart = cart;
  }

  public getCart(): Cart {
    return this.cart;
  }

  public add(product: any | Product, quantity: number): void {
    const orderItem = this.cart.items.find((item) => item.product.id === product.id)
    if (orderItem != null) {
      this.increaseQuantity(orderItem, quantity);
    } else {
      this.addOrderItem(product, quantity);
    }
  }

  public remove(product: any | Product, quantity: number): void {
    const orderItem = this.cart.items.find((item) => item.product.id === product.id);
    if (orderItem != null) {
      if ((orderItem.quantity - quantity) > 0) {
        this.decreaseQuantity(orderItem, quantity);
      } else {
        this.removeOrderItem(orderItem);
      }
    }
  }

  private addOrderItem(product: any | Product, quantity: number): void {
    const orderItem = new OrderItem(new Product(product.id,
      product.name,
      product.price,
      product.shortDescription,
      product.imageUrl,
      product.promotionPrice), quantity);

    this.cart.items.push(orderItem);
    this.calc(orderItem);
  }

  private removeOrderItem(orderItem: OrderItem): void {
    this.cart.items = this.cart.items.filter((item) => item.product.id !== orderItem.product.id)
    this.calc(orderItem);
  }

  private increaseQuantity(orderItem: OrderItem, quantity: number): void {
    orderItem.quantity += quantity;
    this.calc(orderItem);
  }

  private calc(orderItem: OrderItem): void {
    this.calcOrderItemTotal(orderItem);
    this.calcSubTotal();
    this.calcTotal();
    this.calcCount();
  }

  private decreaseQuantity(orderItem: OrderItem, quantity: number): void {
    orderItem.quantity -= quantity;
    this.calc(orderItem);
  }

  private calcOrderItemTotal(orderItem: OrderItem): void  {
    const price = orderItem.product.promotionPrice != 0 ? orderItem.product.promotionPrice : orderItem.product.price;
    orderItem.total = price * orderItem.quantity;
  }

  private calcSubTotal(): void {
    this.cart.subTotal = this.cart.items.reduce((total, item) => total + item.total, 0);
  }

  private calcTotal(): void {
    this.cart.total = this.cart.subTotal + this.cart.shipping + this.cart.tax;
  }

  private calcCount(): void {
    this.cart.count = this.cart.items.reduce((count, item) => count + item.quantity, 0);
  }
}
