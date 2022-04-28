package jp.co.axa.apidemo.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.axa.apidemo.business.model.Employee;
import jp.co.axa.apidemo.business.services.EmployeeService;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Employee Controller")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    @ApiOperation("Returns list of all employees in the system.")
    public List<Employee> getEmployees() {
    	return employeeService.retrieveEmployees();
    }

    @GetMapping("/employees/{employeeId}")
    @ApiOperation("Returns an employee by its ID")
    public Employee getEmployee(@ApiParam("Searched employee ID")
    	@PathVariable(name = "employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees")
    @ApiOperation("Creates a new employee")
    public Employee saveEmployee(@ApiParam("Employee to create") @RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    @ApiOperation("Deletes employee from database")
    public void deleteEmployee(@ApiParam("ID of mployee to delete")
    	@PathVariable(name = "employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    @PutMapping("/employees/{employeeId}")
    @ApiOperation("Updates fields of an existing employee")
    public Employee updateEmployee(@ApiParam("New employee fields")
    	@RequestBody Employee employee, @ApiParam("ID of employee to update")
        @PathVariable(name = "employeeId") Long employeeId){
    	return employeeService.updateEmployee(employee, employeeId);
    }

}
