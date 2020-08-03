import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router} from "@angular/router";
import {SubSink} from 'subsink';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ProductService} from "../../../../services/product.service";

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit,OnDestroy {
  public productForm: FormGroup;
  public subs = new SubSink();

  constructor(private fb: FormBuilder,
              private router: Router,
              private productService: ProductService) {

  }

  ngOnInit() {
    this.productForm = this.fb.group({
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

  onSubmit() {
    if(this.productForm.valid){

    }
    else {
      //not valid
    }
  }

  hasError(field: string): boolean {
    return field == "" || field == null;
  }

  isValid(field: string) {
    return this.productForm.get(field).invalid && this.productForm.get(field).touched;
  }

}
