import {Component, OnDestroy, OnInit} from '@angular/core';
import {SubSink} from 'subsink';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BehaviorSubject, Observable} from 'rxjs';
import {ActivatedRoute, Router} from '@angular/router';
import {UsersService} from '../../../services/users.service';
import {AuthenticationService} from '../../../services/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styles: [
  ]
})
export class ProfileComponent implements OnInit , OnDestroy {

  public isFailed: boolean;
  public error: String;
  public subs = new SubSink();
  public form: FormGroup;

  private photoFile;
  private userSubject: Observable<any> = new BehaviorSubject({}) ;


  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthenticationService,
              private activeRouter: ActivatedRoute,
              private usersService: UsersService) {
      this.userSubject = usersService.get(authService.currentUser.id);
  }

  ngOnInit(): void {
    // this.form =  this.fb.group({
    //   id: [''],
    //   email: ['', [Validators.required, Validators.email]],
    //   name: ['', Validators.required],
    //   address: ['', Validators.required],
    //   phone: ['', Validators.required],
    // });

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
