package jp.co.axa.apidemo.business.services;

import java.util.List;

import jp.co.axa.apidemo.integration.entities.EmployeeEntity;

public interface EmployeeService {

    public List<EmployeeEntity> retrieveEmployees();

    public EmployeeEntity getEmployee(Long employeeId);

    public void saveEmployee(EmployeeEntity employee);

    public void deleteEmployee(Long employeeId);

    public void updateEmployee(EmployeeEntity employee);
}