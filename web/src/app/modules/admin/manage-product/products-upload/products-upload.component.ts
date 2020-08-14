import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SubSink} from 'subsink';
import {Router} from '@angular/router';
import {ProductService} from '../../../../services/product.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-products-upload',
  templateUrl: './products-upload.component.html',
  styleUrls: ['./products-upload.component.css']
})
export class ProductsUploadComponent implements OnInit {

  private photoFile;
  public form: FormGroup;
  public subs = new SubSink();

  constructor(private fb: FormBuilder,
              private router: Router,
              public authService: AuthenticationService,
              private productService: ProductService) {

  }



  ngOnInit() {
    this.form = this.fb.group({
      filePath: ['', Validators.required],
    });

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
    console.log(this.photoFile);
    console.log(this.authService.currentUser.id)
    formData.append("file", this.photoFile);
    this.subs.add(this.productService.ftpProduct(this.authService.currentUser.id, formData)
      .subscribe(
        (result: any) => {
          console.log(result);
          if(result.status.code == 200) {
            alert("RecordS added successfully");

            this.form.reset();
          }
        },
        error => console.log(error)
      ));
  }

  hasError(field: string): boolean {
    return field == '' || field == null;
  }

  isValid(field: string) {
    return this.form.get(field).invalid && this.form.get(field).touched;
  }


}
