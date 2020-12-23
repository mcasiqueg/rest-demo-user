import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { Observable } from 'rxjs/Observable';
import { DataSource } from '@angular/cdk/collections';
import { User } from '../models/user.model';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  /*dataSource = new UserDataSource(this.userService);
  displayedColumns = ['name, email'];*/


  public users = [];

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUser().subscribe(data => this.users = data);
  }
}
/*
export class UserDataSource extends DataSource<any> {
  constructor(private userService: UserService) {
    super();
  }

  connect(): Observable<User[]> {
    return this.userService.getUser();
  }

  disconnect(){}
}*/