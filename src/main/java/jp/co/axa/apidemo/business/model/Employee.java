package jp.co.axa.apidemo.business.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	@NotBlank
	private String name;
	
	/** Current employee salary**/
	@NotNull
	@Min(value = 0L, message = "The value must be positive")
	private Float salary;
	
	/** Affected department **/
	private Department department;
}
