package jp.co.axa.util.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import jp.co.axa.apidemo.business.model.Department;
import jp.co.axa.apidemo.business.model.Employee;
import jp.co.axa.apidemo.integration.entities.DepartmentEntity;
import jp.co.axa.apidemo.integration.entities.EmployeeEntity;
import jp.co.axa.apidemo.util.mapper.EmployeeMapper;
import jp.co.axa.apidemo.util.mapper.EmployeeMapperImpl;

/**
 * EmployeMapper class test <br/>
 * Testing business object <-> entity object conversions
 * @author Florian
 *
 */
public class EmployeeMapperTest {

	private EmployeeMapper employeeMapper = new EmployeeMapperImpl();
	
	/**
	 * Testing conversion from Employee to EmployeeEntity object
	 * Expected : All fields are mapped from entity to business object
	 */
	@Test
	public void mapEmployeeToBusinessTestOk() {
		// Prepare
		EmployeeEntity emplEntity = new EmployeeEntity();
		emplEntity.setId(1L);
		emplEntity.setName("Bob");
		emplEntity.setSalary(1255.4F);
		final DepartmentEntity depEntity = new DepartmentEntity();
		depEntity.setId(1L);
		depEntity.setLabel("Accounting");
		emplEntity.setDepartment(depEntity);
		// Execute
		final Employee employee = employeeMapper.entityToBusiness(emplEntity);
		// Assert
		assertEquals((Long) 1L, employee.getId());
		assertEquals("Bob", employee.getName());
		assertEquals((Float) 1255.4F, employee.getSalary());
		Department department = employee.getDepartment();
		assertEquals((Long) 1L, department.getId());
		assertEquals("Accounting", department.getLabel());
		emplEntity = null;
		assertNull(employeeMapper.entityToBusiness(emplEntity));
	}
	
	/**
	 * Testing conversion from EmployeeEntity to Employee object
	 * Expected : All fields are mapped from to business object to entity
	 */
	@Test
	public void mapEmployeeToEntityTestOk() {
		// Prepare
		final Employee empl = new Employee();
		empl.setId(1L);
		empl.setName("Bob");
		empl.setSalary(1255.4F);
		final Department dep = new Department();
		dep.setId(1L);
		dep.setLabel("Accounting");
		empl.setDepartment(dep);
		// Execute
		final EmployeeEntity employeeEntity = employeeMapper.businessToEntity(empl);
		// Assert
		assertEquals((Long) 1L, employeeEntity.getId());
		assertEquals("Bob", employeeEntity.getName());
		assertEquals((Float) 1255.4F, employeeEntity.getSalary());
		DepartmentEntity departmentEntity = employeeEntity.getDepartment();
		assertEquals((Long) 1L, departmentEntity.getId());
		assertEquals("Accounting", departmentEntity.getLabel());
		assertNull(employeeMapper.businessToEntity(null));
	}
	
	/**
	 * Testing conversion from List of EmployeeEntity to List of Employee object
	 * Expected : All fields are mapped from to business object to entity
	 */
	@Test
	public void mapEntityToBusinessListTestOk() {
		// Prepare
		EmployeeEntity emplEntity = new EmployeeEntity();
		emplEntity.setId(1L);
		emplEntity.setName("Bob");
		emplEntity.setSalary(1255.4F);
		final DepartmentEntity depEntity = new DepartmentEntity();
		depEntity.setId(1L);
		depEntity.setLabel("Accounting");
		emplEntity.setDepartment(depEntity);
		// Execute
		List<Employee> employees = employeeMapper.entityToBusiness(Arrays.asList(emplEntity));
		// Assert
		assertEquals(1, employees.size());
		Employee employee = employees.get(0);
		assertEquals((Long) 1L, employee.getId());
		assertEquals("Bob", employee.getName());
		assertEquals((Float) 1255.4F, employee.getSalary());
		Department department = employee.getDepartment();
		assertEquals((Long) 1L, department.getId());
		assertEquals("Accounting", department.getLabel());
		List<EmployeeEntity> empsNull = null;
		assertNull(employeeMapper.entityToBusiness(empsNull));
	}
}
