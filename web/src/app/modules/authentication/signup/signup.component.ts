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
  public isVendorSignup: boolean = false;

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
      address: ['', Validators.required],
      phone: ['', Validators.required],
      role: this.fb.group({id: ['3']}),
      card: this.fb.group({
        holderName: [''],
        cardType: this.fb.group({id: ['1']}),
        cardNumber: [''],
        ccv: [''],
        expireDate: ['']
      }),
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

  onSignupAsVendorChanged(e) {
    this.isVendorSignup = e.target.checked;
    let holderName = this.signupForm.get('card.holderName');
    let cardNumber = this.signupForm.get('card.cardNumber');
    let ccv = this.signupForm.get('card.ccv');
    let expireDate = this.signupForm.get('card.expireDate');
    let role = this.signupForm.get('role.id');
    if (e.target.checked) {
      role.setValue('2'); //Todo: map to constant Role 2: vendor
      holderName.setValidators(Validators.required);
      cardNumber.setValidators([Validators.required, Validators.pattern('^\\d{16}$')]);
      ccv.setValidators([Validators.required, Validators.pattern('^\\d{3}$')]);
      expireDate.setValidators([Validators.required, Validators.pattern('^\\d{2}/\\\d{2}$')]);
    } else {
      role.setValue('3'); //Todo: map to constant Role 3: Registered
      holderName.clearValidators();
      holderName.reset('');
      cardNumber.clearValidators();
      cardNumber.reset('');
      ccv.clearValidators();
      ccv.reset('');
      expireDate.clearValidators();
      expireDate.reset('');
    }
  }

  // onFileSelect(event) {
  //   if (event.target.files.length > 0) {
  //     const file = event.target.files[0];
  //     this.signupForm.get('photoUrl').setValue(file);
  //   }
  // }

  onSubmit(): void {
    if (this.signupForm.invalid) {
      return;
    }

    this.subs.add(this.authService.register(this.signupForm.value)
      .subscribe(
        (result: any) => {
          // tslint:disable-next-line:triple-equals
          if(result.error && result.errCode == 1002) {
            this.userEmailFail = true;
            this.userEmailIsAlreadyTakenErrorMessage = result.error;
          } else if(result.status.code == 200) {
            this.router.navigate(['/login']);
          }
        },
        error => console.log(error)
      ));
  };

}
