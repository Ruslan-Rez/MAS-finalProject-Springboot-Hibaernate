package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class Employee extends Person{

    @NotBlank
    private String skills;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    abstract Double yearlyIncome();

    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true)
    private HRworker hRworker;

    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true)
    private Administrator administrator;

    public void setHRworker(HRworker hRworker){ //dynamic
        if(this.administrator != null){
            this.administrator.setEmployee(null);
            this.setAdministartor(null);
        }
        if(hRworker!= null){
            hRworker.setEmployee(this);
        }
        this.hRworker = hRworker;
    }
    public void setAdministartor(Administrator administrator){ //dynamic
        if(this.hRworker != null){
            this.hRworker.setEmployee(null);
            this.setAdministartor(null);
        }
        if(administrator!= null){
            administrator.setEmployee(this);
        }
        this.administrator = administrator;
    }
}
