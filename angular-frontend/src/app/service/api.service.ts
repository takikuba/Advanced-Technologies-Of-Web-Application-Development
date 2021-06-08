import {HttpClient, HttpHeaders, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {serialize} from '../shared/utilities/serialize';
import {Observable} from 'rxjs';
import {catchError, filter, map} from 'rxjs/operators';
import { UserService } from './user.service';

export enum RequestMethod {
  Get = 'GET',
  Head = 'HEAD',
  Post = 'POST',
  Put = 'PUT',
  Delete = 'DELETE',
  Options = 'OPTIONS',
  Patch = 'PATCH'
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  headers = new HttpHeaders({
    Accept: 'application/json',
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {
  }

  get(path: string, args?: any): Observable<any> {
    const options = {
      headers: this.headers,
      withCredentials: true,
      params: undefined
    };

    if (args) {
      options.params = serialize(args);
    }

    return this.http.get(path, options)
      .pipe(catchError(this.checkError.bind(this)));
  }

  post(path: string, body: any, customHeaders?: HttpHeaders): Observable<any> {
    console.log("POST method!");
    return this.request(path, body, RequestMethod.Post, customHeaders);
  }

  put(path: string, body: any): Observable<any> {
    return this.request(path, body, RequestMethod.Put);
  }

  delete(path: string, body?: any): Observable<any> {
    return this.request(path, body, RequestMethod.Delete);
  }

  fetchPost(path: string, body: any): Promise<any> {
    // console.log("POST!");
    // console.log(JSON.stringify(body));
    return fetch(path, {
                                method: "POST", 
                                credentials: "include", 
                                body: JSON.stringify(body),
                                headers: {"Content-Type": "application/json"}}).then(data => data.json());
  }


  fetchPut(path: string, body: any): Promise<any> {
    // console.log("DELETE!");
    // console.log(body);
    return fetch(path, {
                                method: "PUT", 
                                credentials: "include", 
                                body: JSON.stringify(body),
                                headers: {"Content-Type": "application/json"}}).then(data => data.json());
  }

  fetchDelete(path: string, body: any): Promise<any> {
    // console.log("PUT!");
    // console.log(JSON.stringify(body));
    return fetch(path, {
                                method: "DELETE", 
                                credentials: "include", 
                                body: JSON.stringify(body),
                                headers: {"Content-Type": "application/json"}}).then(data => data.json());
  }

  private request(path: string, body: any, method = RequestMethod.Post, custemHeaders?: HttpHeaders): Observable<any> {
    const req = new HttpRequest(method, path, body, {
      headers: custemHeaders || this.headers,
      withCredentials: true
    });

    return this.http.request(req).pipe(filter(response => response instanceof HttpResponse))
      .pipe(map((response: HttpResponse<any>) => response.body))
      .pipe(catchError(error => this.checkError(error)));
  }

  private checkError(error: any): any {
    if (error && error.status === 401) {
      console.log("User unauthorized! 401!");
    } else {
      console.log("User athorize! NOT 401!")
    }
    throw error;
  }

  fetchDeleteUser(path: string): Promise<any> {
    // console.log("PUT!");
    // console.log(JSON.stringify(body));
    return fetch(path, {
                                method: "DELETE", 
                                credentials: "include", 
                                headers: {"Content-Type": "application/json"}}).then(data => data.json());
  }

}
