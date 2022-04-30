package jp.co.axa.apidemo.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.axa.apidemo.integration.entities.EmployeeEntity;

/**
 * JPA repository performing SQL queries on table EMPLOYEE
 * @author Florian
 *
 */
@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
