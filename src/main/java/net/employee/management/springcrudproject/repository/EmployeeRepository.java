package net.employee.management.springcrudproject.repository;

import net.employee.management.springcrudproject.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
