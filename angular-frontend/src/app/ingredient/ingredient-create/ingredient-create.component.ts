import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user/user.service';
import { Ingredient } from '../ingredient';
import { IngredientService } from '../ingredient.service';

@Component({
  selector: 'app-ingredient-create',
  templateUrl: './ingredient-create.component.html',
  styleUrls: ['./ingredient-create.component.css']
})
export class IngredientCreateComponent implements OnInit {

  ingredient: Ingredient = new Ingredient();

  constructor(
    private ingredientService: IngredientService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  saveIngredient(){
    this.ingredientService.createIngredient(this.ingredient).subscribe(data =>{
      this.goToIngredientList();
    },
    error => console.log('error!!!' + error));
  }

  goToIngredientList(){
    this.router.navigate(['/ingrediens']);
  }

  onSubmit(){
    this.saveIngredient();
  }

}
