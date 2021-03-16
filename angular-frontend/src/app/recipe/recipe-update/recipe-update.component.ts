import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'app-recipe-update',
  templateUrl: './recipe-update.component.html',
  styleUrls: ['./recipe-update.component.css']
})
export class RecipeUpdateComponent implements OnInit {

  id: number;
  recipe: Recipe = new Recipe();

  constructor(
    private recipeService: RecipeService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.recipe = new Recipe();
    this.id = this.route.snapshot.params['id'];

    this.recipeService.getRecipeById(this.id).subscribe(data => {
      console.log(data);
      this.recipe = data;
      
    }, error => console.log(error));
  }

  
  goToRecipeList(){
    this.router.navigate(['/recipes']);
  }

  onSubmit(){
    this.recipeService.updateRecipe(this.id, this.recipe).subscribe(data => {
      console.log(data);
      this.recipe = new Recipe();
      this.goToRecipeList();
    },
    error => console.log(error));
  }

}
