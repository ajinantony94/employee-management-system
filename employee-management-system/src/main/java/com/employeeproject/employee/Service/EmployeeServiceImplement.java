package com.employeeproject.employee.Service;

import com.employeeproject.employee.Entity.EmployeeEntity;
import com.employeeproject.employee.Model.EmployeeModel;
import com.employeeproject.employee.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImplement implements EmployeeService{

    @Autowired
    EmployeeRepository emplrepo;

    public EmployeeServiceImplement(EmployeeRepository emplrepo) {
        this.emplrepo = emplrepo;
    }

    @Override
    public EmployeeModel createEmployee(EmployeeModel employee) {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee ,employeeEntity);

        emplrepo.save(employeeEntity);
        return employee;
    }

    @Override
    public List<EmployeeModel> getAllEmpployee() {
        List<EmployeeEntity> employeeEntity = emplrepo.findAll();
        List<EmployeeModel> employees = employeeEntity
                .stream()
                .map(emp -> new EmployeeModel(
                        emp.getId(),
                        emp.getFirstName(),
                        emp.getLastName(),
                        emp.getEmail()))
                .collect(Collectors.toList());

        return employees;
    }



    //simple service for delete mapping
//    public void deleteEmployeeSimple(long id){
//
//        emplrepo.deleteById(id);
//
//
//    }

    @Override
    public Boolean deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = emplrepo.findById(id).get();
        emplrepo.delete(employeeEntity);
        return true;
    }

    @Override
    public EmployeeModel getEmployeeById(Long id) {
//        System.out.println("employee got in service: "+id);

        EmployeeEntity employeeEntity = emplrepo.findById(id).get();
        System.out.println(employeeEntity);
        EmployeeModel employeeModel = new EmployeeModel();
//        BeanUtils.copyProperties(employeeModel,employeeEntity);
        //manually copying the values from the employee entity to the employee model!!!!!
        employeeModel.setFirstName(employeeEntity.getFirstName());
        employeeModel.setId(employeeEntity.getId());
        employeeModel.setLastName(employeeEntity.getLastName());
        employeeModel.setEmail(employeeEntity.getEmail());

        System.out.println(employeeModel);
        return employeeModel;
    }

    @Override
    public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel) {

        EmployeeEntity employeeEntity = emplrepo.findById(id).get();
        employeeEntity.setFirstName(employeeModel.getFirstName());
        employeeEntity.setLastName(employeeModel.getLastName());
        employeeEntity.setEmail(employeeModel.getEmail());

        //to save the updates!!
        emplrepo.save(employeeEntity);
        return employeeModel;
    }

}
