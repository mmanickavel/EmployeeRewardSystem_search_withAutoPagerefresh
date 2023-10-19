import { Component, OnInit } from '@angular/core';
import { Employee } from '../employee'
import { EmployeeService } from '../employee.service'
import { Router } from '@angular/router';
import { Search } from '../search';
import { interval, Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  intervalObservable= interval(4000);
  
  employees: Employee[];
 
  createEmployee: Employee = new Employee();

  searches: Array<Search> = [];
  nameSearch: Search = new Search();
  coinSearch: Search = new Search();

  constructor(private employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit(): void {
    this.getEmployees();
    let refreshObservable = interval(12000).pipe(
      switchMap(() => {
        return this.employeeService.getEmployeesList();
      })
    );
 
    refreshObservable.subscribe(data => {
      this.employees = data;
    });
  }


  private getEmployees(){
    this.employeeService.getEmployeesList().subscribe(data => {
      this.employees = data;
    });
  }

  searchEmployees() {
    if(this.nameSearch.value != null){
    this.nameSearch.field = "name";
    this.searches.push(this.nameSearch);
    }
    if(this.coinSearch.value != null){
    this.coinSearch.field = "coins";
    this.searches.push(this.coinSearch);
    }
    this.employeeService.searchEmployeesList(this.searches).subscribe( data => {
      this.employees = data;
    })
   this.searches = [];
   this.nameSearch = {} as Search;
   this.coinSearch = {} as Search; 
  }

  saveEmployee(){
    this.employeeService.createEmployee(this.createEmployee).subscribe( data =>{
      console.log(data);
      this.getEmployees();      
    this.createEmployee = {} as Employee;
    },
    error => console.log(error));
  }
  
  onSubmit(){
    console.log(this.createEmployee);
    this.saveEmployee();
  }

}
