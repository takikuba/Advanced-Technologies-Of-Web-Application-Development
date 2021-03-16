import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Unit } from './unit';

@Injectable({
  providedIn: 'root'
})
export class UnitService {

  private baseURL = "http://localhost:8080/api/v1/units";

  constructor(private httpClient: HttpClient) { }

  getUnitList(): Observable<Unit[]>{
    return this.httpClient.get<Unit[]>(`${this.baseURL}`);
  }

  createUnit(unit: Unit): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, unit);
  }
  
}
