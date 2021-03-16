import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ingredient } from './ingredient';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private baseURL = "http://localhost:8080/api/v1/ingredients";

  constructor(private httpClient: HttpClient) { }

  getIngredientList(): Observable<Ingredient[]> {
    return this.httpClient.get<Ingredient[]>(`${this.baseURL}`);
  }

  createIngredient(ingredient: Ingredient): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, ingredient);
  }

}
