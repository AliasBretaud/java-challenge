package jp.co.axa.apidemo.business.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.business.exceptions.EmployeeNotFoundException;
import jp.co.axa.apidemo.business.model.Employee;
import jp.co.axa.apidemo.integration.entities.EmployeeEntity;
import jp.co.axa.apidemo.integration.repositories.EmployeeRepository;
import jp.co.axa.apidemo.util.mapper.EmployeeMapper;
/**
 * Employee business service implementation
 * @author Florian
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * {@inheritDoc}
     */
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * {@inheritDoc}
     */
    public List<Employee> retrieveEmployees() {
        return employeeMapper.entityToBusiness(employeeRepository.findAll());
    }

    /**
     * {@inheritDoc}
     */
    public Employee getEmployee(Long employeeId) {
        EmployeeEntity emp = employeeRepository.findById(employeeId)
        		.orElseThrow(() -> 
        			new EmployeeNotFoundException(String.format("No employee found with ID %d", employeeId)));
        return employeeMapper.entityToBusiness(emp);
    }

    /**
     * {@inheritDoc}
     */
    public Employee saveEmployee(Employee employee){
        EmployeeEntity emp = employeeRepository.save(employeeMapper.businessToEntity(employee));
        return employeeMapper.entityToBusiness(emp);
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
    public Employee updateEmployee(Employee employee) {
        EmployeeEntity emp = employeeRepository.save(employeeMapper.businessToEntity(employee));
        return employeeMapper.entityToBusiness(emp);
    }

}