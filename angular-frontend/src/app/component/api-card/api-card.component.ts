import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { Event, Router } from '@angular/router';
import { ConfigService, UserService } from 'app/service';

@Component({
  selector: 'app-api-card',
  templateUrl: './api-card.component.html',
  styleUrls: ['./api-card.component.scss']
})
export class ApiCardComponent implements OnInit {

  @Input() title: string;
  @Input() likeB: number;
  @Input() dislikeB: number;
  @Input() kcal: string;
  @Input() time: string;
  @Input() imgUrl: string;
  @Input() content: string;
  @Input() apiText: string;
  @Input() responseObj: any;
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
    console.log(this.responseObj);
  }


  responsePanelClass() {
    const rClass = ['response'];
    if (this.expand) {
      rClass.push('expand');
    }
    if (this.responseObj.status) {
      this.responseObj.status === 200 ?
        rClass.push('response-success') :
        rClass.push('response-error');
    }
    return rClass.join(' ');
  }

  onButtonClick(){
      this.router.navigate(['recipes', this.id]);
  }

}
