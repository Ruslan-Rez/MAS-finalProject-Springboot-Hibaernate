package s22753.mas.finalproject.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import s22753.mas.finalproject.model.Department;

import java.util.Optional;

public interface DepartmentRepository extends CrudRepository<Department,Long> {
   @Query("from Department as d left join fetch d.members where d.id = :id")
    public Optional<Department> findById(Long id);
}
