package jp.co.axa.apidemo.business.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Department business class
 * 
 * @author Florian
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class Department {

	/** Department ID **/
	private Long id;
	
	/** Department designation **/
	private String label;
}
