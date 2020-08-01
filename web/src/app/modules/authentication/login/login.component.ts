import {Component, OnDestroy, OnInit} from '@angular/core';
import {SubSink} from 'subsink';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../../services/authentication.service';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit , OnDestroy {

 public subs = new SubSink();
 public loginFailed: boolean;
 public message: string;
  public loginForm: FormGroup;

  constructor(private fb: FormBuilder,
              private router: Router,
              private authService: AuthenticationService) {

    if (this.authService.isAuthenticated) {
      this.router.navigate(['/']);
    }
  }

  // tslint:disable-next-line:typedef
  public ngOnInit() {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  public ngOnDestroy() {
    this.subs.unsubscribe();
  }
  public hasError(controlName, validationType) {
    return this.loginForm.get(controlName).errors &&
      this.loginForm.get(controlName).errors[validationType] &&
      this.loginForm.get(controlName).touched;
  }
  public isValid(controlName) {
    return this.loginForm.get(controlName).invalid &&
      this.loginForm.get(controlName).touched;
  }

  public onSubmit(): void {

    if (this.loginForm.invalid) {
      return;
    }
    this.subs.add(this.authService.login(this.loginForm.value)
      .pipe(first())
      .subscribe((result: any) => {
          if (result.error) {
            return this.failedLoginHandler(result.error);
          } else {
            return this.router.navigate(['home']);
          }
        },
        result => this.failedLoginHandler(result.error),
      ));
  };

  private failedLoginHandler(message): void {
    this.loginFailed = true;
    this.message = message;
  }
}
