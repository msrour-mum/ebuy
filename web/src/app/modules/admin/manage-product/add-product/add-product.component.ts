import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router} from '@angular/router';
import {SubSink} from 'subsink';
import {ProductService} from '../../../../services/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit, OnDestroy {
  private photoFile;
  public form: FormGroup;
  public subs = new SubSink();

  constructor(private fb: FormBuilder,
              private router: Router,
              private productService: ProductService) {

  }

  ngOnInit() {
    this.form = this.fb.group({
      category: ['', [Validators.required]],
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      shortdesc: ['', [Validators.required]],
      imageUrl: ['', [Validators.required]],
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
    return field == '' || field == null;
  }

  isValid(field: string) {
    return this.form.get(field).invalid && this.form.get(field).touched;
  }

}
