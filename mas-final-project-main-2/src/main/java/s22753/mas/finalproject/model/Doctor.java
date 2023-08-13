package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Doctor extends Person{
    @Min(1000)
    private Double salary;

    @NotBlank
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Department dept; //basic

    @ManyToMany //basic
    @JoinTable( name = "doctor_procedure",
               joinColumns = {@JoinColumn(name = "doctor_id",foreignKey = @ForeignKey(name = "fk_procedure_doctor"))},
               inverseJoinColumns = {@JoinColumn(name = "procedure_id",foreignKey = @ForeignKey(name = "fk_doctor_procedure"))}
    )
    private Set<Procedure> procedures;


    @Override
    String getInfo() {

        return "Name: "+ this.getFirstName()+ " "+ this.getLastName()+ " Salary: "+ this.getSalary()+ " Specialization: "
                + this.getSpecialization() + "Department: "+ this.getDept().getDeptName();
    }


}
