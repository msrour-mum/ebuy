import { Component, Output } from '@angular/core';
import { AppConfig } from './config/app.config';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { ShoppingService } from './services/shopping.service';

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
    public shoppingService: ShoppingService,
    private fb: FormBuilder,
    private router: Router) {

    this.hostUrl = AppConfig.settings.apiServiceUrl;
    console.log('Hi there ');


    //Test call config
    console.log(AppConfig.settings.apiServiceUrl);

  }

  onSubmit(): void {
    let search = this.searchForm.get('search').value;
    this.searchForm.reset();
    this.router.navigate(['search'], { queryParams: { q: search } });
  }

  reportMyOrders(): void {

    this.shoppingService.reportMyOrders(this.authService.currentUser.id).subscribe(x => {
      console.log(x.data);
      let rep = x.data;
      if (rep == '') {
        alert("No data found for this report")
      }
      else {

        window.open(rep, "_blank");
      }
    });

  }


  reportProfit(): void {

    this.shoppingService.reportProfit(this.authService.currentUser.id).subscribe(x => {
      console.log(x.data);
      let rep = x.data;
      if (rep == '') {
        alert("No data found for this report")
      }
      else {

        window.open(rep, "_blank");
      }
    });

  }


}
