import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SubSink} from 'subsink';
import {UsersService} from '../../../../services/users.service';
import {BehaviorSubject, Observable} from 'rxjs';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit , OnDestroy {

  public isFailed: boolean;
  public error: String;
  public roles = [
    {id: 1, name: 'Admin'},
    {id: 2, name: 'Vendor'},
    {id: 1, name: 'Registered'}];

  public subs = new SubSink();
  public form: FormGroup;

  private photoFile;
  private userSubject: Observable<any> = new BehaviorSubject({}) ;


  constructor(private fb: FormBuilder,
              private router: Router,
              private activeRouter: ActivatedRoute,
              private usersService: UsersService) {

    // if (this.authService.isAuthenticated) {
    //   this.router.navigate(['/']);
    activeRouter.params.subscribe((p) => {
      this.userSubject = usersService.get(p.id);
    });
  }

  ngOnInit() {

    this.form =  this.fb.group({
      id: [''],
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      address: ['', Validators.required],
      phone: ['', Validators.required],
    });

    this.subs.add(this.userSubject
      .subscribe({
          next: (result) => {
            const user = result.data;
            this.form =  this.fb.group({
              id: [user.id],
              email: [user.email, [Validators.required, Validators.email]],
              name: [user.name, Validators.required],
              address: [user.address, Validators.required],
              phone: [user.phone, Validators.required],
            });
          },
        error: (err) => { console.log(err); },
        },
      ));

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
    formData.append("user", JSON.stringify(this.form.value));
    formData.append("file", this.photoFile);


    this.subs.add(this.usersService.update(formData)
      .subscribe(
        (result: any) => {
         if(result.status.code == 200) {
            this.router.navigate(['/login']);
          }
        },
        error => console.log(error)
      ));
  };

}
