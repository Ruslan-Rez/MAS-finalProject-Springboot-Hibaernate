package s22753.mas.finalproject;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import s22753.mas.finalproject.model.MedicalRecord;
import s22753.mas.finalproject.model.Patient;
import s22753.mas.finalproject.repository.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "data.setup")
public class DataSetup implements ApplicationRunner {


    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    private boolean executed;

    public DataSetup(PatientRepository patientRepository, MedicalRecordRepository medicalRecordRepository) {

        this.patientRepository = patientRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }


    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

            // Data setup logic

            Patient pt1 = Patient.builder()
                    .firstName("Jay")
                    .lastName("Keigh")
                    .birthDate(LocalDate.of(2000,10,22))
                    .phoneNumber("233090321")
                    .build();
            Patient pt2 = Patient.builder()
                    .firstName("Elle")
                    .lastName("EMayo")
                    .birthDate(LocalDate.of(2010,10,12))
                    .phoneNumber("233080321")
                    .build();
            Patient pt3 = Patient.builder()
                    .firstName("Axe")
                    .lastName("Dee")
                    .birthDate(LocalDate.of(1978,12,26))
                    .phoneNumber("233698321")
                    .build();
            patientRepository.saveAll(List.of(pt1,pt2,pt3));

        MedicalRecord mr1 = MedicalRecord.builder()
                .patient(pt1)
                .recordDate(LocalDate.of(2023,3,12))
                .height(165.0)
                .weight(40.0)
                .bloodSugar(99d)
                .build();
        MedicalRecord mr2 = MedicalRecord.builder()
                .patient(pt1)
                .recordDate(LocalDate.of(2023,4,10))
                .height(165.0)
                .weight(42.0)
                .bloodSugar(90d)
                .build();
        MedicalRecord mr3 = MedicalRecord.builder()
                .patient(pt1)
                .recordDate(LocalDate.of(2023,5,15))
                .height(165.0)
                .weight(40.0)
                .bloodSugar(99d)
                .build();


            medicalRecordRepository.saveAll(Arrays.asList(mr1,mr2,mr3));
    }

}

