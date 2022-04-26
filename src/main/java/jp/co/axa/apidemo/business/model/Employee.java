package jp.co.axa.apidemo.business.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Employee business class
 * 
 * @author Florian
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
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
