import {Injectable} from '@angular/core';
import {ApiService} from './api.service';
import {ConfigService} from './config.service';
import { Observable } from 'rxjs';
import {map} from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { User } from 'app/shared/models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseURL = "http://localhost:8080/api/";
  currentUser;

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

}
