import { Component, OnInit } from '@angular/core';
import { UnitService } from '../unit.service';
import { Unit } from '../unit';

@Component({
  selector: 'app-unit-list',
  templateUrl: './unit-list.component.html',
  styleUrls: ['./unit-list.component.css']
})
export class UnitListComponent implements OnInit {

  units: Unit[];

  constructor(private unitService: UnitService) { }

  ngOnInit(): void {
    this.getUnits();
  }

  private getUnits(){
    this.unitService.getUnitList().subscribe( data => {
      this.units = data;
    })
  }

}
