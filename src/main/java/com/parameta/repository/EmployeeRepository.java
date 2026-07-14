package com.parameta.repository;

import com.parameta.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByDocumentNumber(String numeroDocumento);
}