import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/service';
import { Unit } from 'app/shared/models/unit';

@Component({
  selector: 'app-admin-unit',
  templateUrl: './admin-unit.component.html',
  styleUrls: ['./admin-unit.component.css']
})
export class AdminUnitComponent implements OnInit {

  units: Unit[];
  unit: Unit = new Unit;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getunits();
  }

  private getunits(){
    this.userService.getUnitList().subscribe(data => {
      this.units = data;
    });
  }

  private addUnit() {
    this.userService.addUnit(this.unit).then(data => {
      console.log(data);
      this.units = data;
      this.unit.name = "";
    });
  }

  private unitDelete(unit: Unit){
    this.userService.deleteUnit(unit).then(data => {
      this.units = data;
    });
  }

}
