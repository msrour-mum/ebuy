import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SubSink} from "subsink";
import {Router} from "@angular/router";
import {CategoryService} from "../../../../services/category.service";

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.css']
})
export class EditCategoryComponent implements OnInit {

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
      let formData = new FormData();
      formData.append("name", JSON.stringify(this.categoryForm.get('category')));
      this.subs.add(this.categoryService.update(formData)
        .subscribe(
          (result: any) => {
            if(result.status.code == 200) {
              console.log("Done")
              this.categoryForm.reset();
              this.router.navigate(['/categories']);

            }
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

  isValid(category: string) {
    return this.categoryForm.get(category).invalid && this.categoryForm.get(category).touched;
  }

}
