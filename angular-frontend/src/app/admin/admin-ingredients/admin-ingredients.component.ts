import { Component, OnInit } from '@angular/core';
import { UserService } from 'app/service';
import { IngredientName } from './ingredient-name';

@Component({
  selector: 'app-admin-ingredients',
  templateUrl: './admin-ingredients.component.html',
  styleUrls: ['./admin-ingredients.component.css']
})
export class AdminIngredientsComponent implements OnInit {

  ingredient: IngredientName = new IngredientName;
  ingredients: IngredientName[];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getIngredients();
  }

  private getIngredients(){
    this.userService.getIngredientsList().subscribe(data => {
      this.ingredients = data;
      // console.log(data);
    });
  }

  private addIngredient(){
    this.userService.addIngredient(this.ingredient).then(data => {
      this.ingredients = data;
      this.ingredient.name = "";
    });
  }

  private deleteIngredient(ingredient: IngredientName){
    this.userService.deleteIngredient(ingredient).then(data => {
      this.ingredients = data;
    })
  }

}
