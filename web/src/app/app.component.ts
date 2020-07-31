import {Component, Output} from '@angular/core';
import {AppConfig} from './config/app.config';
import {FormBuilder, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web';
  @Output() hostUrl;
  searchForm: FormGroup = this.fb.group(
    {
      search: ['']
    });

  constructor(public authService: AuthenticationService,
              private  fb: FormBuilder,
              private router: Router) {

    this.hostUrl = AppConfig.settings.apiServiceUrl;
    console.log('Hi there ');


    //Test call config
    console.log(AppConfig.settings.apiServiceUrl);

  }

  onSubmit(): void {
    let search = this.searchForm.get('search').value;
    this.searchForm.reset();
    this.router.navigate(['search'], {queryParams: {q: search}});
  }

}
