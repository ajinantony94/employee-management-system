package com.employeeproject.employee.Service;

import com.employeeproject.employee.Model.EmployeeModel;

import java.util.List;

public interface EmployeeService {


    EmployeeModel createEmployee(EmployeeModel employee);

    List<EmployeeModel> getAllEmpployee();

    Boolean deleteEmployee(Long id);

    EmployeeModel getEmployeeById(Long id);

    EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel);

//    void deleteEmployee(long id);  //simple abstract method to for delete mapping
}
