import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {

  private apiUrl = '/api';
  private userUrl = this.apiUrl + '/users';

  private _refreshTokenUrl = this.apiUrl + '/refresh';

  get refreshTokenUrl(): string {
    return this._refreshTokenUrl;
  }

  private _loginUrl = this.apiUrl + '/login';

  get loginUrl(): string {
    return this._loginUrl;
  }

  private _logoutUrl = this.apiUrl + '/logout';

  get logoutUrl(): string {
    return this._logoutUrl;
  }

  private _changePasswordUrl = this.apiUrl + '/changePassword';

  get changePasswordUrl(): string {
    return this._changePasswordUrl;
  }

  private _whoamiUrl = this.apiUrl + '/whoami';

  get whoamiUrl(): string {
    return this._whoamiUrl;
  }

  private _usersUrl = this.userUrl + '';

  get usersUrl(): string {
    return this._usersUrl;
  }

  get recipesUrl(): string {
    return this.apiUrl + '/recipes';
  }

  recipesUrlById(id: number): string {
    console.log(this.apiUrl + '/recipes/' + id);
    return this.apiUrl + '/recipes/' + id;
  }

  private _fooUrl = this.apiUrl + '/foo';

  get fooUrl(): string {
    return this._fooUrl;
  }

  private _signupUrl = this.apiUrl + '/signup';

  get signupUrl(): string {
    return this._signupUrl;
  }

}