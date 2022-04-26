package jp.co.axa.apidemo.business.model;

import lombok.Data;

/**
 * Employee business class
 * 
 * @author Florian
 *
 */
@Data
public class Employee {
	
	/** Employee ID **/
	private Long id;
	
	/** Employee name**/
	private String name;
	
	/** Current employee salary**/
	private Float salary;
	
	/** Affected department **/
	private Department department;
}
