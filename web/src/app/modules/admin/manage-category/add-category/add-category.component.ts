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
      name: ['', [Validators.required]],
    });
  }

  ngOnDestroy(): void {
    //throw new Error("Method not implemented.");
    this.subs.unsubscribe();
  }

  onSubmit() {
    if(this.categoryForm.valid){
      let formData = new FormData();
      //formData.append("name", JSON.stringify(this.categoryForm.get('category')));
      formData.append("data", JSON.stringify(this.categoryForm.value));
      console.log(this.categoryForm.value)
      this.subs.add(this.categoryService.create(this.categoryForm.value)
        .subscribe(
          (result: any) => {
            if(result.status.code == 200) {
              //console.log("Done")
               alert("Record added successfully");
              this.categoryForm.reset();
              this.router.navigate(['/categories']);

            }
            console.log(result)
          },
          error => console.log(error)
        ));
    }
    else {
      //not valid
    }
  }

  hasError(category: string): boolean {
    return category == "" || category == null;
  }

  isValid(name: string) {
    return this.categoryForm.get(name).invalid && this.categoryForm.get(name).touched;
  }
}
