import {Component, OnInit} from '@angular/core';
import {AuthService, UserService} from '../../service';
import {Router} from '@angular/router';
import { Authority } from 'app/shared/models/authority';
import { AutofillMonitor } from '@angular/cdk/text-field';
import { AdminGuard } from 'app/guard';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private guard: AdminGuard,
    private router: Router
  ) {
  }

  ngOnInit() {
  }

  logout() {
    this.authService.logout().subscribe(res => {
      this.router.navigate(['/login']);
    });
  }

  hasSignedIn() {
    return !!this.userService.currentUser;
  }

  userName() {
    const user = this.userService.currentUser;
    return user.firstname + ' ' + user.lastname;
  }

  isAdmin() {
    if(JSON.stringify(this.userService.currentUser.authorities).search('ROLE_ADMIN') !== -1)
      return true;
    return false;
  }

}
