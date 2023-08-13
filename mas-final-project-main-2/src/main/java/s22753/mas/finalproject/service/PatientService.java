package s22753.mas.finalproject.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s22753.mas.finalproject.repository.PatientRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;
}
