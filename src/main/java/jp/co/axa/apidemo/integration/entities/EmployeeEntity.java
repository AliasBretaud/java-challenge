package jp.co.axa.apidemo.integration.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Employee Entity class (EMPLOYEE table)
 * 
 * @author Florian
 *
 */
@Entity
@Table(name="EMPLOYEE")
@Getter
@Setter
public class EmployeeEntity extends BaseEntity {

	/** Employee name **/
	@Column(name="EMPLOYEE_NAME")
    private String name;

	/** Current employee salary**/
    @Column(name="EMPLOYEE_SALARY")
    private Float salary;

    /** Affected department **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEPARTMENT_ID")
    private DepartmentEntity department;
    
}
