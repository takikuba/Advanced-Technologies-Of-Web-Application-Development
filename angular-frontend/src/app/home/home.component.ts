import { Route } from '@angular/compiler/src/core';
import {Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'app/shared/models/recipe';
import { User } from 'app/shared/models/user';
import { environment } from 'environments/environment';
import {ConfigService, FooService, UserService} from '../service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  fooResponse = {};
  whoamIResponse = {};
  allUserResponse = {};
  users: User[];
  recipes: Recipe[];

  constructor(
    private config: ConfigService,
    private fooService: FooService,
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.getRecipes();
  }

  getRecipes(){
    this.userService.getAllRecipes().subscribe( data => {
      this.recipes = data;
      console.log(data);
    })
  }

  getUsers() {
    this.userService.getAllUsers().subscribe( data => {
      this.users = data;
      console.warn(data);
    })
  }

  recipeView(id) {
    this.router.navigate(['recipes', id]);
  }

  forgeResonseObj(obj, res, path) {
    obj.path = path;
    obj.method = 'GET';
    if (res.ok === false) {
      // err
      obj.status = res.status;
      try {
        obj.body = JSON.stringify(JSON.parse(res._body), null, 2);
      } catch (err) {
        console.log(res);
        obj.body = res.error.message;
      }
    } else {
      // 200
      obj.status = 200;
      obj.body = JSON.stringify(res, null, 2);
    }
  }



  sortTime(){
    var sorted: Recipe[] = this.recipes.sort((obj1, obj2) => {
      if (obj1.time > obj2.time) {
        return 1;
    }

    if (obj1.time < obj2.time) {
        return -1;
    }

    return 0;
    })
    this.recipes = sorted;
  }

  sortKcal(){
    var sorted: Recipe[] = this.recipes.sort((obj1, obj2) => {
      if (obj1.kcal > obj2.kcal) {
        return 1;
    }

    if (obj1.kcal < obj2.kcal) {
        return -1;
    }

    return 0;
    })
    this.recipes = sorted;
  }

  sortLike(){
    var sorted: Recipe[] = this.recipes.sort((obj1, obj2) => {
      if (obj1.likeB > obj2.likeB) {
        return 1;
    }

    if (obj1.likeB < obj2.likeB) {
        return -1;
    }

    return 0;
    })
    this.recipes = sorted;
  }

  sortDislike(){
    var sorted: Recipe[] = this.recipes.sort((obj1, obj2) => {
      if (obj1.dislikeB > obj2.dislikeB) {
        return 1;
    }

    if (obj1.dislikeB < obj2.dislikeB) {
        return -1;
    }

    return 0;
    })
    this.recipes = sorted;
  }

}
