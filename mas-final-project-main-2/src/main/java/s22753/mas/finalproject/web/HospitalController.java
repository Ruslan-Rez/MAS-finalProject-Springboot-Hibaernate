package s22753.mas.finalproject.web;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import s22753.mas.finalproject.service.PatientService;
import s22753.mas.finalproject.model.MedicalRecord;
import s22753.mas.finalproject.model.Patient;
import s22753.mas.finalproject.repository.MedicalRecordRepository;
import s22753.mas.finalproject.repository.PatientRepository;
import s22753.mas.finalproject.service.MedicalRecordService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/hospital")
@AllArgsConstructor
public class HospitalController {
    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final MedicalRecordRepository medicalRecordRepository;
    @Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/patients")
    public String getPatients(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patientsList";
    }

    @GetMapping("/patients/{patientId}")
    public String getMDForPatient(@PathVariable Long patientId, Model model) throws Exception {
        Patient selectedPatient = patientRepository.findById(patientId).orElse(null);
        String patientName = selectedPatient.getFirstName() +" "+ selectedPatient.getLastName();
        model.addAttribute("patientName", patientName);
        if (selectedPatient != null) {
            Set<MedicalRecord> medicalRecords = selectedPatient.getMedicalRecords();
            model.addAttribute("medicalRecords",medicalRecords);
        }
        return "medicalRecordsList";
    }
    @GetMapping("/add/{patientId}")
    public String showAddMedicalRecordForm(@PathVariable Long patientId, Model model) {
        Patient selectedPatient = patientRepository.findById(patientId).orElse(null);
        String patientName = selectedPatient.getFirstName() +" "+ selectedPatient.getLastName();
        model.addAttribute("patientName", patientName);
        if (selectedPatient != null) {
            model.addAttribute("patient", selectedPatient);
            model.addAttribute("medicalRecord", new MedicalRecord());
        }
        return "form";
    }
    @PostMapping("/add/{patientId}")
    public String addMedicalRecord(@PathVariable Long patientId, @ModelAttribute("medicalRecord") MedicalRecord medicalRecord, Model model) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {
            medicalRecord.setPatient(patient);
            medicalRecordRepository.save(medicalRecord);
        }
        return "redirect:/hospital/patients/" +patientId;
    }

}
