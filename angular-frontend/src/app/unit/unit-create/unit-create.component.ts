import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Unit } from '../unit';
import { UnitService } from '../unit.service';

@Component({
  selector: 'app-unit-create',
  templateUrl: './unit-create.component.html',
  styleUrls: ['./unit-create.component.css']
})
export class UnitCreateComponent implements OnInit {

  unit: Unit = new Unit();

  constructor(private unitService: UnitService,
    private router: Router) { }

  ngOnInit(): void {
  }

  saveUnit(){
    this.unitService.createUnit(this.unit).subscribe(data => {
      this.goToUnitList();
    },
    error => console.log(error));
  }

  goToUnitList(){
    this.router.navigate(['/units']);
  }

  onSubmit(){
    this.saveUnit();
  }

}
