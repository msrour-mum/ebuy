import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {SubSink} from "subsink";
import {Router, ActivatedRoute} from "@angular/router";
import {CategoryService} from "../../../../services/category.service";
import { Observable, BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-edit-category',
  templateUrl: './edit-category.component.html',
  styleUrls: ['./edit-category.component.css']
})
export class EditCategoryComponent implements OnInit {

  public categoryForm: FormGroup;
  public subs = new SubSink();
  private productSubject: Observable<any> = new BehaviorSubject({}) ;
  constructor(private fb: FormBuilder,
              private router: Router,
              private activeRouter: ActivatedRoute,
              private categoryService: CategoryService) {

                activeRouter.params.subscribe((p) => {
                  this.productSubject = categoryService.getOne(p.categoryId);
                });

  }

  ngOnInit() {
    this.categoryForm = this.fb.group({
      name: ['', [Validators.required]],
    });

    this.subs.add(this.productSubject
      .subscribe({
          next: (result) => {
            const pro = result.data;


            this.categoryForm =  this.fb.group({
              id: [pro.id],
              name: [pro.name, [Validators.required]],
            });
          },
        error: (err) => { console.log(err); },
        },
      ));

  }

  ngOnDestroy(): void {
    this.subs.unsubscribe();
  }

  onSubmit() {
    if(this.categoryForm.valid){
      this.subs.add(this.categoryService.update(this.categoryForm.value)
        .subscribe(
          (result: any) => {
            if(result.status.code == 200) {
              alert("Record updated successfully");
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

  isValid(name: string) {
    return this.categoryForm.get(name).invalid && this.categoryForm.get(name).touched;
  }

}
