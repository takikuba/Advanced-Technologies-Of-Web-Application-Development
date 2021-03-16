import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { Recipe } from '../recipe/recipe';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/api/v1/users";

  constructor(private httpClient: HttpClient) { }

  getUserList(): Observable<User[]>{
    return this.httpClient.get<User[]>(`${this.baseURL}`);
  }

  getUserRecipes(id: number) {
    return this.httpClient.get<Recipe[]>(`${this.baseURL}/${id}/recipes`);
  }

  createUser(user: User): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, user);
  }

  getUserById(id: number): Observable<User> {
    return this.httpClient.get<User>(`${this.baseURL}/${id}`)
  }

  updateUser(id: number, user: User): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, user);
  }

  deleteUser(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  viewUser(id: number): Observable<User> { //TODO view user with recipes
    return this.getUserById(id); 
  }

}
