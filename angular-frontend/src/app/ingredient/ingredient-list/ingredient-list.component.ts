import { Component, OnInit } from '@angular/core';
import { Ingredient } from '../ingredient';
import { IngredientService } from '../ingredient.service';

@Component({
  selector: 'app-ingredient-list',
  templateUrl: './ingredient-list.component.html',
  styleUrls: ['./ingredient-list.component.css']
})
export class IngredientListComponent implements OnInit {

  ingredients: Ingredient[];

  constructor(private ingredientService: IngredientService) { }

  ngOnInit(): void {
    this.getIngredients();
  }

  private getIngredients(){
    this.ingredientService.getIngredientList().subscribe( data => {
      this.ingredients = data;
    })
  }

}
