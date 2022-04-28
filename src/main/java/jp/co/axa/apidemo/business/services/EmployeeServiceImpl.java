package jp.co.axa.apidemo.business.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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
@Validated
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeMapper employeeMapper;

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> retrieveEmployees() {
    	List<EmployeeEntity> emps = employeeRepository.findAll();
        return employeeMapper.entityToBusiness(emps);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getEmployee(final Long employeeId) {
        EmployeeEntity emp = employeeRepository.findById(employeeId)
        		.orElseThrow(() -> 
        			new EmployeeNotFoundException(String.format("No employee found with ID %d", employeeId)));
        return employeeMapper.entityToBusiness(emp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee saveEmployee(final @Valid Employee employee){
        EmployeeEntity emp = employeeRepository.save(employeeMapper.businessToEntity(employee));
        return employeeMapper.entityToBusiness(emp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteEmployee(final Long employeeId){
    	
    	// Verify that employee actually exists
    	checkEmployeeExists(employeeId);
        employeeRepository.deleteById(employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee updateEmployee(final @Valid Employee employee, final Long employeeId) {
    	
    	// Verify that employee actually exists
    	checkEmployeeExists(employeeId);
    	
    	EmployeeEntity empEntity = employeeMapper.businessToEntity(employee);
        return employeeMapper.entityToBusiness(employeeRepository.save(empEntity));
    }
    
    private void checkEmployeeExists(final Long employeeId) {
    	if (employeeId == null || !employeeRepository.existsById(employeeId)) {
    		throw new EmployeeNotFoundException(String.format("No employee found with ID %d", employeeId));
    	}
    }

}