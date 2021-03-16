import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes: Recipe[];

  constructor(
    private recipeService: RecipeService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getRecipes();
  }

  private getRecipes(){
    this.recipeService.getRecipeList().subscribe( data => {
      this.recipes = data;
    })
  }

  recipeUpdate(id: number) {
    this.router.navigate(['recipe-update', id]);
  }

  recipeDelete(id: number) {
    this.recipeService.deleteRecipe(id).subscribe(data => {
      console.log(data);
      this.getRecipes();
    })
  }

  recipeView(id: number) {
    this.router.navigate(['recipe-view', id]);
  }

}
