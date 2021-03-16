import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/recipe/recipe';
import { RecipeListComponent } from 'src/app/recipe/recipe-list/recipe-list.component';
import { RecipeService } from 'src/app/recipe/recipe.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.css']
})
export class UserViewComponent implements OnInit {

  id: number;
  user: User;
  recipes: Recipe[];

  constructor(private route: ActivatedRoute,
    private userService: UserService,
    private recipeService: RecipeService
    ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.userService.getUserById(this.id).subscribe(data => {
      this.user = data;
    })
    this.getRecipes();
  }


  getRecipes() {
    this.id = this.route.snapshot.params['id'];
    this.userService.getUserRecipes(this.id).subscribe( data => {
      this.recipes = data;
      console.log(data);
    })
  }

}
