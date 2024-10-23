package com.employeeproject.employee.Controller;


import com.employeeproject.employee.Model.EmployeeModel;
import com.employeeproject.employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/employee")
    public EmployeeModel  createEmployee(@RequestBody EmployeeModel employee){
        return employeeService.createEmployee(employee);
    }

    @GetMapping("/employee")
    public List<EmployeeModel> getAllEmployee(){
        return employeeService.getAllEmpployee();
    }


    //simple method to delete something
//    @DeleteMapping("/employee/{id}")
//    public void deleteEmployeeSimple(@PathVariable Long id){
//        employeeService.deleteEmployee(id);
//    }


    //delete mapping with response entity
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
        Boolean deleted = false;
        deleted = employeeService.deleteEmployee(id);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted" ,deleted);
        return ResponseEntity.ok(response);
    }

    //to get the employee for the updation!!
    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id){

        System.out.println("received id: "+id);
        EmployeeModel employeeModel = null;
        employeeModel = employeeService.getEmployeeById(id);
        if (employeeModel == null) {
            return ResponseEntity.notFound().build();  // Return 404 if not found
        }

        return ResponseEntity.ok(employeeModel);  // Return 200 with the employee model if found
    }



    //method to update the current employee
    @PutMapping("/employee/{id}")
    public ResponseEntity<EmployeeModel> updateEmployee( @PathVariable Long id,@RequestBody EmployeeModel employeeModel){

        employeeModel =  employeeService.updateEmployee(id,employeeModel);
        return ResponseEntity.ok(employeeModel);

    }
}
