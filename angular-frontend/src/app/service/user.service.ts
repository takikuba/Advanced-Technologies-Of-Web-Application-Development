import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from 'app/shared/models/user';
import { Tag } from 'app/shared/models/tag';
import { routes } from 'app/app-routing.module';
import { AdminTagsComponent } from 'app/admin/admin-tags/admin-tags.component';
import { ThrowStmt } from '@angular/compiler';
import { Ingredient } from 'app/shared/models/ingredient';
import { IngredientName } from 'app/admin/admin-ingredients/ingredient-name';
import { Unit } from 'app/shared/models/unit';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/api/";
  currentUser: User;

  constructor(
    private apiService: ApiService,
    private config: ConfigService,
    private httpClient: HttpClient
  ) {
  }

  initUser() {
    const promise = this.apiService.get(this.config.refreshTokenUrl).toPromise()
      .then(res => {
        if (res.access_token !== null) {
          return this.getMyInfo().toPromise()
            .then(user => {
              this.currentUser = user;
            });
        }
      })
      .catch(() => null);
    return promise;
  }

  getMyInfo() {
    return this.apiService.get(this.config.whoamiUrl)
      .pipe(map(user => this.currentUser = user));
  }

  getAllUsers() {
    return this.apiService.get(this.config.usersUrl);
  }

  getAllRecipes() {
    return this.apiService.get(this.config.recipesUrl);
  }

  getRecipe(id: number) {
    return this.apiService.get(this.config.recipesUrlById(id));
  }

  getTagList(){
    return this.apiService.get(this.config.tagUrl);
  }

  getIngredientsList(){
    return this.apiService.get(this.config.ingredientsUrl);
  }


  addTag(tag: Tag): Promise<Tag[]> {
    // console.log("POST!");
    return this.apiService.fetchPost(this.config.tagUrl, tag);
  }

  deleteTag(tag: Tag): Promise<Tag[]> {
    return this.apiService.fetchDelete(this.config.tagUrl, tag);
  }

  addIngredient(ingredient: IngredientName): Promise<IngredientName[]> {
    // console.log("POST!");
    return this.apiService.fetchPost(this.config.ingredientsUrl, ingredient);
  }

  deleteIngredient(ingredient: IngredientName): Promise<IngredientName[]> {
    return this.apiService.fetchDelete(this.config.ingredientsUrl, ingredient);
  }

  getUnitList() {
    return this.apiService.get(this.config.unitsUrl);
  }

  addUnit(unit: Unit): Promise<Unit[]> {
    // console.log("POST!");
    return this.apiService.fetchPost(this.config.unitsUrl, unit);
  }

  deleteUnit(unit: Unit): Promise<Unit[]> {
    return this.apiService.fetchDelete(this.config.unitsUrl, unit);
  }

}  
