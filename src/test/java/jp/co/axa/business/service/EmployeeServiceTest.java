package jp.co.axa.business.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import jp.co.axa.apidemo.business.exceptions.EmployeeNotFoundException;
import jp.co.axa.apidemo.business.model.Department;
import jp.co.axa.apidemo.business.model.Employee;
import jp.co.axa.apidemo.business.services.EmployeeServiceImpl;
import jp.co.axa.apidemo.integration.entities.DepartmentEntity;
import jp.co.axa.apidemo.integration.entities.EmployeeEntity;
import jp.co.axa.apidemo.integration.repositories.EmployeeRepository;
import jp.co.axa.apidemo.util.mapper.EmployeeMapperImpl;

/**
 * Employee business service test class
 * @author Florian
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@Spy
	private EmployeeMapperImpl employeeMapper;
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	/** Generate mock data **/
	private EmployeeEntity getEmployeeEntity() {
		EmployeeEntity emp = new EmployeeEntity();
		emp.setId(1L);
		emp.setName("Bob");
		emp.setSalary(15000.5F);
		emp.setTimestamp(LocalDateTime.now());
		DepartmentEntity dep = new DepartmentEntity();
		dep.setId(1L);
		dep.setLabel("Accounting");
		dep.setTimestamp(LocalDateTime.now());
		emp.setDepartment(dep);
		return emp;
	}
	
	/** Generate mock data **/
	private Employee getEmployee() {
		Employee emp = new Employee();
		emp.setId(1L);
		emp.setName("Bob");
		emp.setSalary(15000.5F);
		Department dep = new Department();
		dep.setId(1L);
		dep.setLabel("Accounting");
		emp.setDepartment(dep);
		return emp;
	}
	
	/**
	 * Testing function EmployeeService.retrieveEmployees()
	 * Expected : Get all employees as a List
	 */
	@Test
	public void retrieveEmployeesTestOk() {
		// Prepare
		when(employeeRepository.findAll()).thenReturn(Arrays.asList(getEmployeeEntity()));
		// Execute
		List<Employee> emps = employeeServiceImpl.retrieveEmployees();
		// Assert
		assertEquals(1, emps.size());
		Employee emp = emps.get(0);
		assertEquals((Long) 1L, emp.getId());
		assertEquals("Bob", emp.getName());
		assertEquals((Float) 15000.5F, emp.getSalary());
		Department dep = emp.getDepartment();
		assertNotNull(dep);
		assertEquals((Long) 1L, dep.getId());
		assertEquals("Accounting", dep.getLabel());
	}
	
	/**
	 * Testing function EmployeeService.getEmployee()
	 * Expected : get an employee by its ID
	 */
	@Test
	public void getEmployeeTestOk() {
		// Prepare
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(getEmployeeEntity()));
		// Execute
		Employee emp = employeeServiceImpl.getEmployee(1L);
		// Assert
		assertEquals((Long) 1L, emp.getId());
		assertEquals("Bob", emp.getName());
		assertEquals((Float) 15000.5F, emp.getSalary());
		Department dep = emp.getDepartment();
		assertNotNull(dep);
		assertEquals((Long) 1L, dep.getId());
		assertEquals("Accounting", dep.getLabel());
	}
	
	/**
	 * Testing function EmployeeService.getEmployee()
	 * Expected : Exception raised because given ID is not found
	 */
	@Test(expected = EmployeeNotFoundException.class)
	public void getEmployeeTestKo() {
		// Prepare
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.ofNullable(null));
		// Execute
		employeeServiceImpl.getEmployee(1L);
	}
	
	/**
	 * Testing function EmployeeService.saveEmployee()
	 * Expected : Given object is saved successfully
	 */
	@Test
	public void saveEmployeeTestOk() {
		// Prepare
		when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(getEmployeeEntity());
		// Execute
		Employee emp = employeeServiceImpl.saveEmployee(getEmployee());
		// Assert
		assertEquals((Long) 1L, emp.getId());
		assertEquals("Bob", emp.getName());
		assertEquals((Float) 15000.5F, emp.getSalary());
		Department dep = emp.getDepartment();
		assertNotNull(dep);
		assertEquals((Long) 1L, dep.getId());
		assertEquals("Accounting", dep.getLabel());
	}
	
	/**
	 * Testing function EmployeeService.updateEmployee()
	 * Expected : Given object is updated successfully
	 */
	@Test
	public void updateEmployeeTestOk() {
		// Prepare
		when(employeeRepository.existsById(anyLong())).thenReturn(true);
		when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(getEmployeeEntity());
		// Execute
		Employee emp = employeeServiceImpl.updateEmployee(getEmployee());
		// Assert
		assertEquals((Long) 1L, emp.getId());
		assertEquals("Bob", emp.getName());
		assertEquals((Float) 15000.5F, emp.getSalary());
		Department dep = emp.getDepartment();
		assertNotNull(dep);
		assertEquals((Long) 1L, dep.getId());
		assertEquals("Accounting", dep.getLabel());
	}
	
	/**
	 * Testing function EmployeeService.updateEmployee()
	 * Exception raised because given ID is not found
	 */
	@Test(expected = EmployeeNotFoundException.class)
	public void updateEmployeeTestKo() {
		// Prepare
		when(employeeRepository.existsById(anyLong())).thenReturn(false);
		// Execute
		employeeServiceImpl.updateEmployee(getEmployee());
	}
	
	/**
	 * Testing function EmployeeService.updateEmployee()
	 * Exception raised because given ID is not found
	 */
	@Test(expected = EmployeeNotFoundException.class)
	public void updateEmployeeTestKo2() {
		// Execute
		employeeServiceImpl.updateEmployee(null);
	}
	
	/**
	 * Testing function EmployeeService.deleteEmployee()
	 * Expected : Employee is deleted successfully
	 */
	@Test
	public void deleteEmployeeTestOk() {
		// Prepare
		when(employeeRepository.existsById(anyLong())).thenReturn(true);
		// Execute
		employeeServiceImpl.deleteEmployee(1L);
		// Assert
		verify(employeeRepository, times(1)).deleteById(anyLong());
	}
	
	/**
	 * Testing function EmployeeService.deleteEmployee()
	 * Expected : Exception raised because given ID is not found
	 */
	@Test(expected = EmployeeNotFoundException.class)
	public void deleteEmployeeTestKo() {
		// Prepare
		when(employeeRepository.existsById(anyLong())).thenReturn(false);
		// Execute
		employeeServiceImpl.deleteEmployee(1L);
	}
	
}
