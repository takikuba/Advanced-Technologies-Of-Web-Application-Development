import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { Event, Router } from '@angular/router';
import { ConfigService, UserService } from 'app/service';


@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['./user-card.component.css']
})
export class UserCardComponent implements OnInit {

  @Input() name: string;
  @Input() surname: string;
  @Input() phone: string;
  @Input() imgUrl: string;
  @Input() content: string;
  @Input() apiText: string;
  @Input() id: number;
  @Input() apiClick: EventEmitter<any> = new EventEmitter();
  expand = false;

  constructor(
    private userService: UserService,
    private router: Router,
    private conf: ConfigService 
  ) {
  }

  ngOnInit() {
  }

  onButtonClick(){
    this.userService.deleteUser(this.id);
    this.router.navigate(['']);
}

}
