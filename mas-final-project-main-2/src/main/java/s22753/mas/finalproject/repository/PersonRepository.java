package s22753.mas.finalproject.repository;

import org.springframework.data.repository.CrudRepository;
import s22753.mas.finalproject.model.Patient;

public interface PersonRepository extends CrudRepository<Patient,Long> {
}
