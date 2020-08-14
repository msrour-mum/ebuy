import { Component, OnInit } from '@angular/core';
import {UsersService} from '../../../../services/users.service';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  search: string;

  public users = new Observable<any[]>();
  constructor(private usersService: UsersService) { }

  ngOnInit() {
    this.users = this.usersService.getAll();

  }

}
