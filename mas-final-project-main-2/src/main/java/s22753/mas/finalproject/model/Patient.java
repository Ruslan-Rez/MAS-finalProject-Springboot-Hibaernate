package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Patient extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate birthDate;
    @NotBlank
    @Column(unique = true)
    @Pattern(regexp = "[0-9]{9}")
    private String phoneNumber;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.REMOVE) //association with an attribute
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Visit> visits = new HashSet<>();

    @OneToMany(mappedBy = "patient",cascade = {CascadeType.REMOVE}) //composition
    @ToString.Exclude
    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Set<MedicalRecord> medicalRecords = new HashSet<>();

    @Override
    String getInfo() {

        return "Name: "+ this.getFirstName()+ " "+ this.getLastName()+ " BirthDate: "+ this.getBirthDate()+ " Phone nuber: "+ this.getPhoneNumber();
    }
}
