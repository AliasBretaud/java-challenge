package jp.co.axa.apidemo.business.model;

import lombok.Data;

/**
 * Department business class
 * 
 * @author Florian
 *
 */
@Data
public class Department {

	/** Department ID **/
	private Long id;
	
	/** Department designation **/
	private String label;
}
