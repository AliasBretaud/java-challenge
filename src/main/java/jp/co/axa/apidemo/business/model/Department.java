package jp.co.axa.apidemo.business.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Department business class
 * 
 * @author Florian
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
@ApiModel(description = "Class representing a department")
public class Department {

	/** Department ID **/
	@ApiModelProperty(notes = "Unique identifier of the department", example = "1", required = true)
	private Long id;
	
	/** Department designation **/
	@ApiModelProperty(notes = "Department designation", example = "IT", required = true)
	private String label;
}
