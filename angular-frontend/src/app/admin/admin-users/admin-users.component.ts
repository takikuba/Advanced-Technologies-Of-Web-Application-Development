import { ThrowStmt } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/service';
import { User } from 'app/shared/models/user';

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {

  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  private getAllUsers(){
    this.userService.getAllUsers().subscribe(data =>{
      this.users = data;
    });
  }

}
