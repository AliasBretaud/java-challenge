package jp.co.axa.apidemo.util.mapper;

import org.mapstruct.Mapper;

import jp.co.axa.apidemo.business.model.Employee;
import jp.co.axa.apidemo.integration.entities.EmployeeEntity;

/**
 * Employee mapper class. <br/>
 * Performs object conversions between Employee business class and entity class
 * @author Florian
 *
 */
@Mapper(componentModel = "spring")
public interface EmployeeMapper {

	/**
	 * Converts an employee entity object to a business object
	 * @param employeeEntity
	 * 			Entity object
	 * @return converted employee business object
	 */
    Employee entityToBusiness(EmployeeEntity employeeEntity);
    
    /**
	 * Converts an employee business object to an entity object
	 * @param employee
	 * 			Employee business object
	 * @return converted employee entity object
	 */
    EmployeeEntity businessToEntity(Employee employee);
}