import { Component, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { UserService } from 'app/service';
import { Recipe } from 'app/shared/models/recipe';

@Component({
  selector: 'app-recipe-view',
  templateUrl: './recipe-view.component.html',
  styleUrls: ['./recipe-view.component.css']
})
export class RecipeViewComponent implements OnInit {

  id: number;
  recipe: Recipe;
  urlSafe: SafeResourceUrl;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    public sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.userService.getRecipe(this.id).subscribe(data => {
      this.recipe = data;
      this.urlSafe= this.sanitizer.bypassSecurityTrustResourceUrl(this.recipe.link);
    })
  }

  addLike(): void {
    // console.log("add like to: " + this.recipe.id);
    this.recipe.likeB += 1;
    this.userService.addLike(this.recipe.id, this.recipe);
  }

  addDislike(): void {
    // console.log("add dislike to: " + this.recipe.id);
    // this.recipe.dislikeB = this.recipe.dislikeB + 1;
    this.userService.addDislike(this.recipe.id, this.recipe);
  }

}
