package s22753.mas.finalproject.repository;


import org.springframework.data.repository.CrudRepository;
import s22753.mas.finalproject.model.Patient;

public interface PatientRepository extends CrudRepository<Patient,Long> {
}
