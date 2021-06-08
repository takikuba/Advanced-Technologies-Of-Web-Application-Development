import { ConditionalExpr } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IngredientName } from 'app/admin/admin-ingredients/ingredient-name';
import { UserService } from 'app/service';
import { Ingredient } from 'app/shared/models/ingredient';
import { Recipe } from 'app/shared/models/recipe';
import { Tag } from 'app/shared/models/tag';
import { Unit } from 'app/shared/models/unit';

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {

  myIngredients: Ingredient[] = [];
  myTags: Tag[] = [];

  ingredientNames: IngredientName[];
  unitNames: Unit[];
  tagNames: Tag[];

  newIngredient = new Ingredient;

  name: string = "";
  count: number = 1;
  unit: string = "";

  newTag = new Tag;
  tagName: string = "";
  tagId: number = 0;

  newRecipe = new Recipe;

  recipeName: string = "";
  recipeKcal: number = 0;
  recipeTime: number = 0;
  recipeDesc: string = "";
  recipeLink: string = "";

  constructor(
    private userServiece: UserService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userServiece.getIngredientsList().subscribe(data=> {
      this.ingredientNames = data;
    })
    this.userServiece.getUnitList().subscribe(data=>{
      this.unitNames = data;
    })
    this.userServiece.getTagList().subscribe(data => {
      this.tagNames = data;
    })

    this.newRecipe.name;
  }


  submitName(name: string){
    this.name = name;
  }


  submitUnit(unit: string){
    this.unit = unit;
  }

  addIngredient(){
    this.newIngredient.name = this.name;
    this.newIngredient.unit = this.unit;
    this.newIngredient.count = this.count;
    console.log(this.newIngredient);
    this.myIngredients.unshift(this.newIngredient);
    this.newIngredient = new Ingredient;
  }
  deleteIngredient(i: Ingredient){
    this.myIngredients.forEach((element, index)=>{
      if(element == i) this.myIngredients.splice(index, 1);
    });
  }


  submitTag(tagName: string){
    this.tagName = tagName;
  }

  addTag(){
    this.newTag.name = this.tagName;
    this.myTags.unshift(this.newTag);
    this.newTag = new Tag;
  }
  deleteTag(tag: Tag){
    this.myTags.forEach((element, index)=>{
      if(element == tag) this.myTags.splice(index, 1);
    });
  }


  addRecipe(){
    this.newRecipe.image = "woda.jpg";
    console.log(this.newRecipe.image);
    this.newRecipe.tags = this.myTags;
    this.newRecipe.ingredients = this.myIngredients;
    console.log(JSON.stringify(this.newRecipe));

    this.userServiece.addRecipe(this.newRecipe);
    this.router.navigate([""]);
  }


}
