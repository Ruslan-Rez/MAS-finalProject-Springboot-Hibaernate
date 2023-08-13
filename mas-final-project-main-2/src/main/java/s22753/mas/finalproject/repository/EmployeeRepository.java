package s22753.mas.finalproject.repository;


import org.springframework.data.repository.CrudRepository;
import s22753.mas.finalproject.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

}
