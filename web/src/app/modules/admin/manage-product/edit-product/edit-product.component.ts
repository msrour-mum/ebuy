import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SubSink} from 'subsink';
import {ActivatedRoute, Router} from '@angular/router';
import {ProductService} from '../../../../services/product.service';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit, OnDestroy {
  private photoFile;
  public form: FormGroup;
  public subs = new SubSink();
  public product;

  constructor(private fb: FormBuilder,
              private router: Router,
              private activeRouter: ActivatedRoute,
              private productService: ProductService) {

    activeRouter.params.subscribe((p) => {
      //console.log(p.productId);
      //this.product = usersService.get(p.productId); //Todo: get product from server
    });

  }

  ngOnInit() {
    this.form = this.fb.group({
      category: ['', [Validators.required]],
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      shortdesc: ['', [Validators.required]],
      imageurl: ['', [Validators.required]],
      cost: ['', [Validators.required]],
      price: ['', [Validators.required]],
    });
  }

  ngOnDestroy(): void {
    //throw new Error("Method not implemented.");
    this.subs.unsubscribe();
  }

  public onFileSelect(event) {
    if (event.target.files.length > 0) {
      this.photoFile = event.target.files[0];
    }
  }

  onSubmit() {
    if (this.form.invalid) {
      return;
    }
    let formData: any = new FormData();
    formData.append("product", JSON.stringify(this.form.value));
    formData.append("file", this.photoFile);
  }

  hasError(field: string): boolean {
    return field == "" || field == null;
  }

  isValid(field: string) {
    return this.form.get(field).invalid && this.form.get(field).touched;
  }

}
