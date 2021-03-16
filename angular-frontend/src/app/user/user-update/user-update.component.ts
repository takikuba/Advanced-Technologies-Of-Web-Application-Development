import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  id: number;
  user: User = new User();

  constructor(private userService: UserService, 
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.user = new User();
    this.id = this.route.snapshot.params['id'];

    this.userService.getUserById(this.id).subscribe(data => {
      console.log(data);
      this.user = data;
      
    }, error => console.log(error));
  
  }

  goToUserList(){
    this.router.navigate(['/users']);
  }

  onSubmit(){
    this.userService.updateUser(this.id, this.user).subscribe(data => {
      console.log(data);
      this.user = new User();
      this.goToUserList();
    },
    error => console.log(error));
  }

}
