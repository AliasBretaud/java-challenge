package jp.co.axa.apidemo.integration.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract class containing common fields applied to Entity classes
 * 
 * @author Florian
 *
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
	
	/** Primary key **/
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
	
	/** Date of last object modification **/
	@Column(name="LAST_UPDATE")
    private LocalDateTime timestamp;

	@PrePersist
    @PreUpdate
    private void setUp() {
		// Update timestamp before save or update
    	this.timestamp = LocalDateTime.now();
    }
}
