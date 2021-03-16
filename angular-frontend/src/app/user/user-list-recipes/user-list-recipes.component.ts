import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/recipe/recipe';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-list-recipes',
  templateUrl: './user-list-recipes.component.html',
  styleUrls: ['./user-list-recipes.component.css']
})
export class UserListRecipesComponent implements OnInit {

  recipes: Recipe[];

  constructor(
    private userService: UserService
  ) { }

  ngOnInit(): void {
  }

  getRecipesList(id: number) {
    this.userService.getUserRecipes(id).subscribe( data => {
      this.recipes = data;
    })
  }

}
