package s22753.mas.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import s22753.mas.finalproject.model.MedicalRecord;
import s22753.mas.finalproject.model.Patient;
import s22753.mas.finalproject.repository.MedicalRecordRepository;
import s22753.mas.finalproject.repository.PatientRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class Composition {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;
    @PersistenceContext
    private EntityManager entityManager;

    Patient pa1;
    Patient pa2;
    MedicalRecord md1;
    MedicalRecord md2;

    @BeforeEach
    public void initData() {
        pa1 = Patient.builder()
                .firstName("Joe")
                .lastName("Kerry")
                .birthDate(LocalDate.of(1996, 05, 23))
                .phoneNumber("456987567")
                .build();
        pa2 = Patient.builder()
                .firstName("Joe")
                .lastName("Kerry")
                .birthDate(LocalDate.of(1996, 05, 23))
                .phoneNumber("456987567")
                .build();
        md1 = MedicalRecord.builder()
                .patient(pa1)
                .height(123d)
                .weight(23d)
                .bloodSugar(112d)
                .recordDate(LocalDate.of(2023,06,12))
                .build();
        md2 = MedicalRecord.builder()
                .patient(pa1)
                .height(123d)
                .weight(25d)
                .bloodSugar(112d)
                .recordDate(LocalDate.of(2023,07,17))
                .build();
    }
    @Test
    public void testRequiredDependencies() {
        assertNotNull(patientRepository);
        assertNotNull(medicalRecordRepository);
    }
    @Test
    public void testSave(){
        patientRepository.save(pa1);
        medicalRecordRepository.saveAll(Arrays.asList(md1,md2));
        entityManager.flush();
        assertEquals(1,patientRepository.count());
        assertEquals(2,medicalRecordRepository.count());
        assertNotNull(pa1.getMedicalRecords());
        System.out.println(medicalRecordRepository.findByPatient(pa1));
        System.out.println(patientRepository.findAll());

    }



}
