package s22753.mas.finalproject.repository;

import org.springframework.data.repository.CrudRepository;
import s22753.mas.finalproject.model.MedicalRecord;
import s22753.mas.finalproject.model.Patient;


import java.util.List;

public interface MedicalRecordRepository extends CrudRepository<MedicalRecord,Long> {
    List<MedicalRecord> findByPatient(Patient patient);
}
