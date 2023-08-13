package s22753.mas.finalproject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import s22753.mas.finalproject.model.Patient;
import s22753.mas.finalproject.model.Procedure;
import s22753.mas.finalproject.model.Visit;
import s22753.mas.finalproject.repository.PatientRepository;
import s22753.mas.finalproject.repository.ProcedureRepository;
import s22753.mas.finalproject.repository.VisitRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class AssociationWithAnAttributeTest {
    @Autowired
    private ProcedureRepository procedureRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private VisitRepository visitRepository;
    @PersistenceContext
    private EntityManager entityManager;

    Procedure p1;
    Patient pa1;
    Visit v1;

    @BeforeEach
    public void initData(){
        p1 = Procedure.builder()
                .name("Pr3")
                .price(300.0)
                .build();
        pa1 = Patient.builder()
                .firstName("Joe")
                .lastName("Kerry")
                .birthDate(LocalDate.of(1996,05,23))
                .phoneNumber("456987567")
                .build();

        Visit.setHospital("LENOX HILL HOSPITAL");
        v1 = Visit.builder()
                .dateOfVisit(LocalDate.of(2023,12,12))
                .patient(pa1)
                .procedure(p1)
                .visitKind(new HashSet<>(Arrays.asList(Visit.VisitType.CONSULTATION, Visit.VisitType.SURGERY)))
                .build();


    }
    @Test
    public void testRequiredDependencies() {
        assertNotNull(procedureRepository);
        assertNotNull(patientRepository);
        assertNotNull(visitRepository);

    }
    @Test
    public void testSave(){

        procedureRepository.save(p1);
        patientRepository.save(pa1);
        visitRepository.save(v1);
        entityManager.flush();
        assertEquals(p1,v1.getProcedure());
        assertEquals(pa1,v1.getPatient());
    }

}