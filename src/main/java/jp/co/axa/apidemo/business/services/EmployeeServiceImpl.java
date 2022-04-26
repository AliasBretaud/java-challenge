package jp.co.axa.apidemo.business.services;

import jp.co.axa.apidemo.integration.entities.EmployeeEntity;
import jp.co.axa.apidemo.integration.repositories.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeEntity> retrieveEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees;
    }

    public EmployeeEntity getEmployee(Long employeeId) {
        Optional<EmployeeEntity> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    public void saveEmployee(EmployeeEntity employee){
        employeeRepository.save(employee);
    }

    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    public void updateEmployee(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }
}