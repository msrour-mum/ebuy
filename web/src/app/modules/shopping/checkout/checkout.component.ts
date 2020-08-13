import {Component, OnInit} from '@angular/core';
import {ShoppingService} from '../../../services/shopping.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MerchantService} from '../../../services/merchant.service';
import {SubSink} from 'subsink';
import {Cart} from '../../../models/shopping/cart';
import {Product} from '../../../models/shopping/product';
import {UsersService} from '../../../services/users.service';
import {AuthenticationService} from '../../../services/authentication.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styles: []
})
export class CheckoutComponent implements OnInit {

  public form: FormGroup;
  public error: string;
  public showError: boolean = false;

  private subs = new SubSink();
  private cart: Cart;

  constructor(public shoppingService: ShoppingService,
              private fb: FormBuilder,
              private router: Router,
              private merchantService: MerchantService,
              private userService: UsersService,
              private authService: AuthenticationService) {

  }

  ngOnInit(): void {

    this.subs.add(this.shoppingService.getCart().subscribe((data) => this.cart = data));
    this.subs.add(this.userService.get(this.authService.currentUser.id).subscribe((result) => {
        const user = result.data;
        console.log(user);
        this.form = this.fb.group({
          checkoutOptions: this.fb.group(
            {
              saveUpdateMyAddress: [true],
              saveUpdateMyCard: [true],
            },
          ),
          address: [user.address, Validators.required],
          card: this.fb.group({
            holderName: [user.card ? user.card.holderName : '', Validators.required],
            cardType: this.fb.group(user.card ? { id: [`${user.card.cardType.id}`]} : {id: ['1']}),
            cardNumber: [user.card ? user.card.cardNumber : '', [Validators.required, Validators.pattern('^\\d{16}$')]],
            ccv: [user.card ? user.card.ccv : '', [Validators.required, Validators.pattern('^\\d{3}$')]],
            expireDate: [user.card ? user.card.expireDate : '', [Validators.required, Validators.pattern('^\\d{2}/\\\d{2}$')]],
          }),
        });
      }));
  }

  public hasError(controlName, validationType) {
    return this.form.get(controlName).errors &&
      this.form.get(controlName).errors[validationType] &&
      (this.form.get(controlName).touched);
  }

  public isValid(controlName) {
    return this.form.get(controlName).invalid &&
      this.form.get(controlName).touched;
  }

  public closeError(): void {
    this.showError = false;
    this.error ='';
  }

  public onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    this.subs.add(this.merchantService.pay(this.form.get('card').value, this.cart.total)
      .subscribe((result: any) => {
          if (result.error) {
            this.showError = true;
            this.error = result.error;
          } else if (result.data == false) {
            this.showError = true;
            this.error = "Invalid credit card";
          } else {
            //Call checkout;
            let checkout = {
              card: this.form.get('card').value,
              address: this.form.get('address').value,
              checkoutOptions:  this.form.get('checkoutOptions').value,
              shipping: this.cart.shipping,
              tax: this.cart.tax,
              items: this.cart.items.map((i) => {
                return { productId: i.product.id, quantity: i.quantity};
              }),
            };

            this.shoppingService.checkout(checkout).subscribe((result) => {
              if (result.data == true) {
                this.shoppingService.clearCart();
                this.router.navigate(['/home']);
              } else {
                this.error = 'error while checking out, please try again later';
                this.showError = true;
              }
            },
              (error) => {this.error = error; this.showError = true; });
          }
        },
        error => console.log(error)
      ));
  }
}
