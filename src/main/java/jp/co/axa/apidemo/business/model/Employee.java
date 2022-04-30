package jp.co.axa.apidemo.business.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Employee business class
 * 
 * @author Florian
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Class representing an employee")
public class Employee {
	
	/** Employee ID **/
	@ApiModelProperty(notes = "Unique identifier of the employee.",	example = "1", required = false)
	private Long id;
	
	/** Employee name**/
	@NotBlank
	@ApiModelProperty(notes = "Name of the employee", example = "Bob", required = true)
	private String name;
	
	/** Current employee salary **/
	@NotNull
	@Min(value = 0L, message = "The value must be positive")
	@ApiModelProperty(notes = " Current employee salary", example = "1500", required = true)
	private Float salary;
	
	/** Affected department **/
	@ApiModelProperty(notes = "Department of the employee", required = false)
	private Department department;
}
