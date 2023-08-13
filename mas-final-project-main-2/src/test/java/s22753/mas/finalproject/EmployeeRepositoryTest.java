package s22753.mas.finalproject;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import s22753.mas.finalproject.model.Administrator;
import s22753.mas.finalproject.model.HRworker;
import s22753.mas.finalproject.repository.AdministratorRepository;
import s22753.mas.finalproject.repository.EmployeeRepository;
import s22753.mas.finalproject.repository.HRWorkerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private HRWorkerRepository hrWorkerRepository;
    @Autowired
    private AdministratorRepository adminRepository;

    @PersistenceContext
    private EntityManager entityManager;

    HRworker h1;
    Administrator a1, a2;
    @BeforeEach
    public void initData(){
        a1 = Administrator.builder()
                .firstName("Jenny")
                .lastName("Jenkins")
                .workingHours(7d)
                .hourlyRate(30d)
                .skills("Working")
                .build();
        a2 = Administrator.builder()
                .firstName("John")
                .lastName("Johnson")
                .workingHours(12d)
                .hourlyRate(35d)
                .skills("Reading")
                .build();
        h1 = HRworker.builder()
                .firstName("Samantha")
                .lastName("Jones")
                .salary(30000d)
                .bonus(100d)
                .performanceRating(4)
                .skills("Judging")
                .build();
    }
    @Test
    public void testRequiredDependencies(){
        assertNotNull(employeeRepository);
        assertNotNull(hrWorkerRepository);
        assertNotNull(adminRepository);
    }

    @Test
    public void testSaveAll(){
        adminRepository.saveAll(Arrays.asList(a1, a2));
        hrWorkerRepository.save(h1);
        entityManager.flush();
        assertEquals(3,employeeRepository.count());
    }

}