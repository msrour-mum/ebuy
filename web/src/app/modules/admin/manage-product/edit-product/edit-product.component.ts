import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Router, ActivatedRoute} from '@angular/router';
import {SubSink} from 'subsink';
import {ProductService} from '../../../../services/product.service';
import { CategoryService } from 'src/app/services/category.service';
import { Observable, BehaviorSubject } from 'rxjs';
import { AppConfig } from 'src/app/config/app.config';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
export class EditProductComponent implements OnInit, OnDestroy {
  private photoFile;
  public product: any;
  public form: FormGroup;
  public subs = new SubSink();
  public lstCategory =[];
  private productSubject: Observable<any> = new BehaviorSubject({}) ;
  public img=null;
  public hostUrl: string;
  constructor(private fb: FormBuilder,
              private router: Router,
              private activeRouter: ActivatedRoute,
              private productService: ProductService,
              private categoryService:CategoryService) {


                activeRouter.params.subscribe((p) => {
                  this.productSubject = productService.getOne(p.productId);
                });

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

    this.getCategories();
    this.hostUrl = AppConfig.settings.apiServiceUrl;
    this.subs.add(this.productSubject
      .subscribe({
          next: (result) => {
            this.product = result.data;
            this.img= this.product.imageUrl;
            this.form =  this.fb.group({
              id: [this.product.id],
              category: this.fb.group({ id: [this.product.category.id, [Validators.required]]}),
              user: this.fb.group({ id: [this.product.user.id]}),
              productStatus: this.fb.group({ id: [this.product.productStatus.id, [Validators.required]]}),
              name: [this.product.name, [Validators.required]],
              published: [this.product.published, [Validators.required]],
              service: [this.product.service, [Validators.required]],
              deleted: [this.product.deleted, [Validators.required]],

              description: [this.product.description, [Validators.required]],
              shortDescription: [this.product.shortDescription, [Validators.required]],
              imageUrl: ['' ],
              cost: [this.product.cost, [Validators.required]],
              price: [this.product.price, [Validators.required]],
            });
          },
        error: (err) => { console.log(err); },
        },
      ));

  }

  ngOnDestroy(): void {
    this.subs.unsubscribe();
  }

  public onFileSelect(event) {
    if (event.target.files.length > 0) {
      this.photoFile = event.target.files[0];
    }
  }

  onSubmit() {
    console.log(this.form.invalid)
    if (this.form.invalid) {
      return;
    }
    let formData: any = new FormData();
    if(this.form.value.imageUrl=='')
      this.form.value.imageUrl=this.img;
    formData.append("productJson", JSON.stringify(this.form.value));
    formData.append("file", this.photoFile);

    this.subs.add(this.productService.update(formData,this.form.value.id)
      .subscribe(
        (result: any) => {
         if(result.status.code == 200) {
            //this.router.navigate(['/login']);

           alert("Record updated successfully");

          }
        },
        error => console.log(error)
      ));
  }

  hasError(field: string): boolean {
    return field == "" || field == null;
  }

  isValid(field: string) {
    return this.form.get(field).invalid && this.form.get(field).touched;
  }


  getCategories() {
    this.categoryService.getActive().subscribe( {
      next: (result)=> {
       this.lstCategory = result.data;

      },
      error: (err)=> console.log(err.console.error())
     });

 }

}
