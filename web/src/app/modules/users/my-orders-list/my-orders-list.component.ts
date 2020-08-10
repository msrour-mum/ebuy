import {Component, OnDestroy, OnInit} from '@angular/core';
import {UsersService} from '../../../services/users.service';
import {Observable} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {SubSink} from 'subsink';
import {AppConfig} from '../../../config/app.config';

@Component({
  selector: 'app-orders-list',
  templateUrl: './my-orders-list.component.html',
  styles: [
  ]
})
export class MyOrdersListComponent implements OnInit, OnDestroy {

  public orders: any[] = [];
  public hostUrl: string = AppConfig.settings.apiServiceUrl;
  private subs = new SubSink();

  constructor(private userService: UsersService,
              private activeRouter: ActivatedRoute) { }

  ngOnInit(): void {

    this.subs.add(this.activeRouter.params.subscribe((p) => {
      this.subs.add(this.userService.getOrders(p.userId).subscribe((result) => {
        this.orders = result.data;
      }));
    }));
  }

  ngOnDestroy() {
    this.subs.unsubscribe();
  }
}
