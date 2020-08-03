import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { CategoryService } from "../../../../services/category.service";
import { Router} from "@angular/router";
import {SubSink} from 'subsink';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit, OnDestroy {

  public categoryForm: FormGroup;
  public subs = new SubSink();

  constructor(private fb: FormBuilder,
              private router: Router,
              private categoryService: CategoryService) {

  }

  ngOnInit() {
    this.categoryForm = this.fb.group({
      category: ['', [Validators.required]],
    });
  }

  ngOnDestroy(): void {
    //throw new Error("Method not implemented.");
    this.subs.unsubscribe();
  }

  onSubmit() {
    if(this.categoryForm.valid){

    }
    else {
      //not valid
    }
  }

  hasError(category: string): boolean {
    return category == "" || category == null;
  }

  isValid(category: string) {
    return this.categoryForm.get(category).invalid && this.categoryForm.get(category).touched;
  }
}
