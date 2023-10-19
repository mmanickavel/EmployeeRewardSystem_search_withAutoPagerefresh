import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { Search } from './search';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private baseURL = "http://localhost:8080/api/coins";

  private baseURLPost = "http://localhost:8080/api/coin";
  
  private searchURL = "http://localhost:8080/api/search";

  constructor(private httpClient: HttpClient) { }
  
  getEmployeesList(): Observable<Employee[]>{
    return this.httpClient.get<Employee[]>(`${this.baseURL}`);
  }

  searchEmployeesList(searches: Search[]): Observable<any>{
    return this.httpClient.post<Employee[]>(`${this.searchURL}`, searches);
  }

  createEmployee(employee: Employee): Observable<Object>{
    return this.httpClient.post(`${this.baseURLPost}`, employee);
  }

}
