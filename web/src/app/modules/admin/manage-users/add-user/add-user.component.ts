import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SubSink } from 'subsink';
import { UsersService } from '../../../../services/users.service';
import { BehaviorSubject, Observable } from 'rxjs';


@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {


  public isFailed: boolean;
  public error: String;
  public roles = [
    { id: 1, name: 'Admin' },
    { id: 2, name: 'Vendor' },
    { id: 1, name: 'Registered' }];

  public subs = new SubSink();
  public form: FormGroup;

  private photoFile;
  public lstRoles =[];
  //private userSubject: Observable<any> = new BehaviorSubject({}) ;

  constructor(private fb: FormBuilder,
    private router: Router,
    private activeRouter: ActivatedRoute,
    private usersService: UsersService) {
      this.getRoles();
    // if (this.authService.isAuthenticated) {
    //   this.router.navigate(['/']);
    //activeRouter.params.subscribe((p) => {this.userSubject = usersService.get(p.id);});
  }

  ngOnInit() {

    this.form = this.fb.group({
      id: [''],
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      role: this.fb.group({ id: ['', [Validators.required]]}),
      address: ['', Validators.required],
      phone: ['', Validators.required],
    });

   
    // this.subs.add(this.userSubject
    // .subscribe({
    // next: (result) => {
    //   const user = result.data;
    //   this.form =  this.fb.group({
    //     id: [user.id],
    //     email: [user.email, [Validators.required, Validators.email]],
    //     name: [user.name, Validators.required],
    //     address: [user.address, Validators.required],
    //     phone: [user.phone, Validators.required],
    //   });
    // },
    // error: (err) => { console.log(err); },
    // },
    // ));
  }

  ngOnDestroy() {
    this.subs.unsubscribe();
  }

  public hasError(controlName, validationType) {
    return this.form.get(controlName).errors &&
      this.form.get(controlName).errors[validationType] &&
      (this.form.get(controlName).touched);
  }
  public isValid(controlName) {
    return this.form.get(controlName).invalid &&
      this.form.get(controlName).touched;
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
    formData.append("userJson", JSON.stringify(this.form.value));
    formData.append("file", this.photoFile);

    this.subs.add(this.usersService.create(formData)
      .subscribe(
        (result: any) => {
          
          if (result.status.code == 200) {
          
            alert("Record added successfully");
            this.form.reset();            
            this.router.navigate(['/app-user-list']);
          }
        },
        error => console.log(error)
      ));
  };


  getRoles() {
    this.usersService.getRoles().subscribe( {
      next: (result)=> {
       this.lstRoles = result.data;
       console.log(this.lstRoles);
      },
      error: (err)=> console.log(err.console.error())
     });

    // console.log(this.lstRoles);

 }

}
