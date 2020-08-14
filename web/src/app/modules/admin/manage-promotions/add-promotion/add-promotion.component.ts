import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../../services/product.service';
import {SubSink} from 'subsink';

@Component({
  selector: 'app-add-promotion',
  templateUrl: './add-promotion.component.html',
  styleUrls: ['./add-promotion.component.css']
})
export class AddPromotionComponent implements OnInit {

  public subs = new SubSink();
  public form: FormGroup;
  public product: any;

  constructor(private fb: FormBuilder,
              private router: Router,
              private activeRouter: ActivatedRoute,
              private productService: ProductService) {

    this.activeRouter.params.subscribe((p) => {
      this.product = this.productService.getOne(p.productId).subscribe((result) => {
        this.product = result.data;

      });
    });
  }

  ngOnInit() {

    this.form = this.fb.group({
      id: [''],
      name: ['', [Validators.required]],
      fromDate: ['', Validators.required],
      toDate: ['', Validators.required],
      discount: ['', Validators.required],
    });
  }

  ngOnDestroy() {
    this.subs.unsubscribe();
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

  onSubmit(): void {
    if (this.form.invalid) {
      return;
    }

    this.subs.add(this.productService.addPromotion(this.form.value, this.product.id)
      .subscribe(
        (result: any) => {
          // tslint:disable-next-line:triple-equals
          if(result.error && result.errCode == 1002) {
          } else if (result.status.code == 200) {
            this.router.navigate(['/promotions', this.product.id]);
          }
        },
        error => console.log(error)
      ));

  }
}
