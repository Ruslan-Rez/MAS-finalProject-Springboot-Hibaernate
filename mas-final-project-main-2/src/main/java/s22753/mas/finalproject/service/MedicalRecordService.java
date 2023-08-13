package s22753.mas.finalproject.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import s22753.mas.finalproject.model.MedicalRecord;
import s22753.mas.finalproject.repository.MedicalRecordRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MedicalRecordService {
    private MedicalRecordRepository medicalRecordRepository;



}
