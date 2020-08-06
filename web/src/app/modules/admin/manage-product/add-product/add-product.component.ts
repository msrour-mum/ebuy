import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router} from '@angular/router';
import {SubSink} from 'subsink';
import {ProductService} from '../../../../services/product.service';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit, OnDestroy {
  private photoFile;
  public form: FormGroup;
  public subs = new SubSink();
  public lstCategory =[];
  constructor(private fb: FormBuilder,
              private router: Router,
              private productService: ProductService,
              private categoryService:CategoryService) {

                this.getCategories();

  }

  ngOnInit() {
    this.form = this.fb.group({
      category: this.fb.group({ id: ['', [Validators.required]]}),
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      shortDescription: ['', [Validators.required]],
      imageUrl: ['', [Validators.required]],
      cost: ['', [Validators.required]],
      price: ['', [Validators.required]],
    });
  }

  ngOnDestroy(): void {
    this.subs.unsubscribe();
  }

 
  public onFileSelect(event) {
    if (event.target.files.length > 0) {
      this.photoFile = event.target.files[0];
    }
  }

  onSubmit(): void {
    
    if (this.form.invalid) {
      return;
    }
    let formData: any = new FormData();   
    formData.append("productJson", JSON.stringify(this.form.value));
    formData.append("file", this.photoFile);
    this.subs.add(this.productService.create(formData)
      .subscribe(
        (result: any) => {
         if(result.status.code == 200) {
            //this.router.navigate(['/login']);
            console.log("Done")
            this.form.reset();
          }
        },
        error => console.log(error)
      ));
  };

  hasError(field: string): boolean {
    return field == '' || field == null;
  }

  isValid(field: string) {
    return this.form.get(field).invalid && this.form.get(field).touched;
  }

  
  getCategories() {
     this.categoryService.getActive().subscribe( {
       next: (result)=> {        
        this.lstCategory = result.data;
        console.log(this.lstCategory);
       },
       error: (err)=> console.log(err.console.error())
      });

  }

}
