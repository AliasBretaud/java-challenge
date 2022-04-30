package jp.co.axa.apidemo.business.services;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jp.co.axa.apidemo.business.exceptions.EmployeeNotFoundException;
import jp.co.axa.apidemo.business.model.Employee;
import jp.co.axa.apidemo.config.CacheConfig;
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
	
	private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(CacheConfig.EMPLOYEES_CACHE_NAME)
    public List<Employee> retrieveEmployees() {
    	List<EmployeeEntity> emps = employeeRepository.findAll();
        return employeeMapper.entityToBusiness(emps);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(CacheConfig.EMPLOYEE_CACHE_NAME)
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
    @Caching(evict = {
            @CacheEvict(value = CacheConfig.EMPLOYEES_CACHE_NAME, allEntries=true),
            @CacheEvict(value = CacheConfig.EMPLOYEE_CACHE_NAME, allEntries=true)})
    public Employee saveEmployee(final @Valid Employee employee){
        EmployeeEntity emp = employeeRepository.save(employeeMapper.businessToEntity(employee));
        LOGGER.info("Employee saved");
        return employeeMapper.entityToBusiness(emp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Caching(evict = {
            @CacheEvict(value="employee", allEntries=true),
            @CacheEvict(value="employees", allEntries=true)})
    public void deleteEmployee(final Long employeeId){
    	
    	// Verify that employee actually exists
    	checkEmployeeExists(employeeId);
        employeeRepository.deleteById(employeeId);
        LOGGER.info("Employee {} deleted", employeeId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee updateEmployee(final @Valid Employee employee, final Long employeeId) {
    	
    	// Verify that employee actually exists
    	checkEmployeeExists(employeeId);
    	
    	EmployeeEntity empEntity = employeeMapper.businessToEntity(employee);
    	LOGGER.info("Employee {} updated", employeeId);
        return employeeMapper.entityToBusiness(employeeRepository.save(empEntity));
    }
    
    private void checkEmployeeExists(final Long employeeId) {
    	if (employeeId == null || !employeeRepository.existsById(employeeId)) {
    		throw new EmployeeNotFoundException(String.format("No employee found with ID %d", employeeId));
    	}
    }

}