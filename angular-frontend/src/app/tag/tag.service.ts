import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable } from 'rxjs';
import { Tag } from './tag';

@Injectable({
  providedIn: 'root'
})
export class TagService {

  private baseURL = "http://localhost:8080/api/v1/tags";

  constructor(private httpClient: HttpClient) { }

  getTagList(): Observable<Tag[]>{
    return this.httpClient.get<Tag[]>(`${this.baseURL}`);
  }

  createTag(tag: Tag): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, tag);
  }
}
