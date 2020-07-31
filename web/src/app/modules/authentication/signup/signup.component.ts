import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {SubSink} from 'subsink';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit, OnDestroy {

  public userEmailFail: boolean;
  // tslint:disable-next-line:ban-types
  public userEmailIsAlreadyTakenErrorMessage: String;

  public subs = new SubSink();
  public signupForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthenticationService) {

    if (this.authService.isAuthenticated) {
      this.router.navigate(['/']);
    }
  }

  ngOnInit() {

    this.signupForm =  this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      password: ['', [Validators.required, Validators.pattern('^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$')]],
      address: this.fb.group({
        street: ['', Validators.required],
        city: ['', Validators.required],
        state: ['', Validators.required],
        zipCode: ['', Validators.required]
      }),
      phone: [''],
      photoUrl: ['', Validators.required]
    });
  }
  ngOnDestroy() {
    this.subs.unsubscribe();
  }

  hasError(controlName, validationType) {
    return this.signupForm.get(controlName).errors &&
      this.signupForm.get(controlName).errors[validationType] &&
      (this.signupForm.get(controlName).touched);
  }
  isValid(controlName) {
    return this.signupForm.get(controlName).invalid &&
      this.signupForm.get(controlName).touched;
  }

  onFileSelect(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.signupForm.get('photoUrl').setValue(file);
    }
  }

  onSubmit(): void {
    if(this.signupForm.invalid) {
      return;
    }
    const formData: any = new FormData();
    formData.append('payload', JSON.stringify(this.signupForm.value));
    formData.append('photo', this.signupForm.get('photoUrl').value);

    this.subs.add(this.authService.register(formData)
      .subscribe(
        (data: any) => {
          // tslint:disable-next-line:triple-equals
          if(data.error && data.error.code == 407) {
            this.userEmailFail = true;
            this.userEmailIsAlreadyTakenErrorMessage = data.error.message;
          } else {
            this.router.navigate(['/login']);
          }
        },
        error => console.log(error)
      ));

  };

}
