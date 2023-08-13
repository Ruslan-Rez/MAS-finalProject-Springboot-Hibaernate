package s22753.mas.finalproject;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import s22753.mas.finalproject.model.Procedure;
import s22753.mas.finalproject.repository.ProcedureRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProcedureRepositoryTest {
    @Autowired
    private ProcedureRepository procedureRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Procedure p1;


    @BeforeEach
    public void iniData(){
        p1 = Procedure.builder()
                .name("Pr3")
                .price(300.0)
                .medication(new HashSet<>(Arrays.asList("Paracetamol","Antibiotic")))
                .build();
    }
    @Test
    public void testRequiredDependencies(){
        assertNotNull(procedureRepository);
    }
    @Test
    public void testFetchProcedures(){
        Iterable<Procedure> all = procedureRepository.findAll();
        for (Procedure p:all){
            System.out.println(p);
        }
    }
    @Test
    public void testSaveProcedure(){
        procedureRepository.save(p1);
        entityManager.flush();
        long count = procedureRepository.count();
        assertEquals(1,count);
    }
    @Test
    public void testSaveInvalidProcedurePrice(){
        assertThrows(ConstraintViolationException.class,()->{
        p1.setPrice(-10000d);
        procedureRepository.save(p1);
        entityManager.flush();});
    }
    @Test
    public void testFindByName(){
        procedureRepository.save(p1);
        entityManager.flush();
       List<Procedure> arthroscopy =  procedureRepository.findByName("Pr3");
       assertEquals(1,arthroscopy.size());

    }
    @Test
    public void testFindByNameAndPrice(){
        procedureRepository.save(p1);
        entityManager.flush();
        List<Procedure> arthrocentesis =  procedureRepository.findByNameAndPrice("Pr3",300d);
        assertEquals(1,arthrocentesis.size());
    }
    @Test
    public void testFindByBudgetHigherThan(){
        procedureRepository.save(p1);
        entityManager.flush();
        List<Procedure> p = procedureRepository.findProcedureWithPriceGreaterThan(200);
        assertEquals(1,p.size());
    }


}