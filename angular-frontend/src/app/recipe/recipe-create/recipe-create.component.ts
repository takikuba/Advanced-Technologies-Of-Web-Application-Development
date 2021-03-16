import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-create',
  templateUrl: './recipe-create.component.html',
  styleUrls: ['./recipe-create.component.css']
})
export class RecipeCreateComponent implements OnInit {

  recipe: Recipe = new Recipe();

  constructor(
    private recipeService: RecipeService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  saveRecipe(){
    this.recipeService.createRecipe(this.recipe).subscribe( data => {
      this.goToRecipeList();
    },
    error => console.log(error));
  }

  goToRecipeList() {
    this.router.navigate(['/recipes']);
  }

  onSubmit() {
    this.saveRecipe();
  }

}
