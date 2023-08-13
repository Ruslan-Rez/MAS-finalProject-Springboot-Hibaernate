package s22753.mas.finalproject;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import s22753.mas.finalproject.model.Department;
import s22753.mas.finalproject.model.Doctor;
import s22753.mas.finalproject.repository.DepartmentRepository;
import s22753.mas.finalproject.repository.DoctorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AssociationTest {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    Department dept1;
    Doctor d1;
    @Test
    public void testRequiredDependencies() {
        assertNotNull(departmentRepository);
        assertNotNull(doctorRepository);
    }
    @BeforeEach
    public void initData(){
        dept1 = Department.builder()
                .deptName("Urgent Care")
                .build();
        d1 = Doctor.builder()
                .firstName("Samantha")
                .lastName("Jones")
                .salary(30000d)
                .specialization("Surgeon")
                .build();
    }
    @Test
    public void testSave(){
        dept1.getMembers().add(d1);
        departmentRepository.save(dept1);
        d1.setDept(dept1);
        doctorRepository.save(d1);

        Optional<Department> byId = departmentRepository.findById(dept1.getId());
        assertTrue(byId.isPresent());
        assertEquals(1, byId.get().getMembers().size());
    }
}
