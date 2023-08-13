package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Min(2)
    private Double weight;
    @Min(63)
    private Double height;
    @Min(0)
    private Double bloodSugar;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @ManyToOne(optional = false) //composition
    @JoinColumn(name = "patient_id",nullable = false,updatable = false)
    @NotNull
    private Patient patient;
}
