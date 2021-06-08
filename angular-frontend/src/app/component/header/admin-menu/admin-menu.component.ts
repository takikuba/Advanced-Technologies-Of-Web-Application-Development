import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, ConfigService, UserService } from 'app/service';

@Component({
  selector: 'app-admin-menu',
  templateUrl: './admin-menu.component.html',
  styleUrls: ['./admin-menu.component.css']
})
export class AdminMenuComponent implements OnInit {

  // TODO define user interface
  user: any;

  constructor(
    private config: ConfigService,
    private authService: AuthService,
    private router: Router,
    private userService: UserService
  ) {
  }

  ngOnInit() {
    this.user = this.userService.currentUser;
  }

}
