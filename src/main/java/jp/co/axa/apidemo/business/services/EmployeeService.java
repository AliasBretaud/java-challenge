package jp.co.axa.apidemo.business.services;

import java.util.List;

import jp.co.axa.apidemo.integration.entities.EmployeeEntity;

/**
 * Employee business service. Employee domain data access and moification
 * 
 * @author Florian
 *
 */
public interface EmployeeService {

	/**
	 * Retrieve all the employees
	 * @return a list containing employees presents in database
	 */
    public List<EmployeeEntity> retrieveEmployees();

    /**
     * Get an employee by its ID
     * @param employeeId
     * 			Employee ID
     * @return retrieved employee
     */
    public EmployeeEntity getEmployee(Long employeeId);

    /**
     * Save a new employee in database
     * @param employee
     * 			Employee object
     */
    public void saveEmployee(EmployeeEntity employee);

    /**
     * Delete an employee in database
     * @param employeeId
     * 			Employee ID
     */
    public void deleteEmployee(Long employeeId);

    /**
     * Update an existing employee in database
     * @param employee
     * 			Employee object
     */
    public void updateEmployee(EmployeeEntity employee);
}