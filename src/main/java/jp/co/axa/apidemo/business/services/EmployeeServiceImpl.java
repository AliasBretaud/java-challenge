package jp.co.axa.apidemo.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.integration.entities.EmployeeEntity;
import jp.co.axa.apidemo.integration.repositories.EmployeeRepository;
/**
 * Employee business service implementation
 * @author Florian
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * {@inheritDoc}
     */
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * {@inheritDoc}
     */
    public List<EmployeeEntity> retrieveEmployees() {
        List<EmployeeEntity> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * {@inheritDoc}
     */
    public EmployeeEntity getEmployee(Long employeeId) {
        Optional<EmployeeEntity> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    /**
     * {@inheritDoc}
     */
    public void saveEmployee(EmployeeEntity employee){
        employeeRepository.save(employee);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    public void updateEmployee(EmployeeEntity employee) {
        employeeRepository.save(employee);
    }
}