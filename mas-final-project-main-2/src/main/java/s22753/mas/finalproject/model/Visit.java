package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"patient_id","procedure_id"})
})
public class Visit {
    public enum VisitType{CONSULTATION,
        SURGERY,EXAMINATION}
    private Double discount; //optional


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate dateOfVisit;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false) //association with an attribute
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "procedure_id",nullable = false) //association with an attribute
    private Procedure procedure;


    @ElementCollection
    @Enumerated(EnumType.STRING) //overlapping
    private Set<VisitType> visitKind = EnumSet.of(VisitType.CONSULTATION);

    @ManyToOne
    @JoinColumn(name = "ward_id") //qualified
    private Ward ward;


    private static String hospital ="LENOX HILL HOSPITAL"; //static attribute


    public static void setHospital(String hospital){
        if(hospital == null || "".equals(hospital.trim())){
            throw new IllegalArgumentException("Hospital cannot be null");
        }
        Visit.hospital = hospital;
    }
    public static String getHospital(){return hospital;}

    @Transient // tells hibernate that this attribute should not be persisted
    public Double getTotal(){ //derived attribute
        if(discount == null){
            return procedure.getPrice();
        } else return procedure.getPrice()-(procedure.getPrice()*discount);
    }

    @NotBlank
    private String diagnosis;

    public String getDiagnosis(){
        if (!visitKind.contains(VisitType.CONSULTATION)){
            throw new IllegalArgumentException("this visit is not a consultation");
        }else {
            return this.diagnosis;
        }
    }
    public void setDiagnosis(String diagnosis){
        if (!visitKind.contains(VisitType.CONSULTATION)){
            throw new IllegalArgumentException("this visit is not a consultation");
        }else {
            this.diagnosis = diagnosis;
        }
    }

    @NotBlank
    private String operationNotes;

    public String getOperationNotes(){
        if (!visitKind.contains(VisitType.SURGERY)){
            throw new IllegalArgumentException("this visit is not a surgery");
        }else {
            return this.operationNotes;
        }
    }
    public void setOperationNotes(String operationNotes){
        if (!visitKind.contains(VisitType.SURGERY)){
            throw new IllegalArgumentException("this visit is not a surgery");
        }else {
            this.operationNotes = operationNotes;
        }
    }
    @NotBlank
    private String examType;

    public String getExamType(){
        if (!visitKind.contains(VisitType.EXAMINATION)){
            throw new IllegalArgumentException("this visit is not an examination");
        }
        return this.examType;
    }
    public void setExamType(String examType){
        if (!visitKind.contains(VisitType.EXAMINATION)){
            throw new IllegalArgumentException("this visit is not an examination");
        }else {
            this.examType = examType;
        }
    }
}
