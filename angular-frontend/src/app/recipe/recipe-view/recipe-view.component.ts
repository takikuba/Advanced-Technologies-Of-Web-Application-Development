import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-recipe-view',
  templateUrl: './recipe-view.component.html',
  styleUrls: ['./recipe-view.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class RecipeViewComponent implements OnInit {

  id: number;
  recipe: Recipe;
  link: string;
  urlSafe: SafeResourceUrl;


  constructor(
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    public sanitizer: DomSanitizer
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.recipeService.getRecipeById(this.id).subscribe(data => {
      this.recipe = data;
      this.urlSafe= this.sanitizer.bypassSecurityTrustResourceUrl(this.recipe.link);
    })
  }

}