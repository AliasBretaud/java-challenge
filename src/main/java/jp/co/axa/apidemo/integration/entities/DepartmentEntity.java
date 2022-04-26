package jp.co.axa.apidemo.integration.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Department Entity class (DEPARTMENT table)
 * 
 * @author Florian
 *
 */
@Entity
@Table(name="DEPARTMENT")
@Getter
@Setter
public class DepartmentEntity extends BaseEntity {
	
	/** Department designation **/
	@Column(name="DEPARTMENT_LABEL")
	private String label;
	
	/** List of employees effected to the department **/
	@OneToMany(mappedBy = "department")
	private List<EmployeeEntity> employees;
	
}
