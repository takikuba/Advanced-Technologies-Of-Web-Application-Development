import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Ingredient } from '../ingredient/ingredient';
import { Recipe } from './recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  private baseURL = "http://localhost:8080/api/v1/recipes";

  constructor(
    private httpClient: HttpClient
  ) { }

    getRecipeList(): Observable<Recipe[]> {
      return this.httpClient.get<Recipe[]>(`${this.baseURL}`);
    }

    createRecipe(recipe: Recipe): Observable<Object> {
      return this.httpClient.post(`${this.baseURL}`, recipe);
    }

    getRecipeById(id: number): Observable<Recipe> {
      return this.httpClient.get<Recipe>(`${this.baseURL}/${id}`);
    }

    updateRecipe(id: number, recipe: Recipe): Observable<Object> {
      return this.httpClient.put(`${this.baseURL}/${id}`, recipe);
    }

    getRecipeIngredients(id: number) {
      return this.httpClient.get<Ingredient[]>(`${this.baseURL}/${id}/ingredients`);
    }

    deleteRecipe(id: number): Observable<Object> {
      return this.httpClient.delete(`${this.baseURL}/${id}`);
    }
  
    viewRecipe(id: number): Observable<Recipe> { //TODO view recipe ingredients
      return this.getRecipeById(id); 
    }

}
