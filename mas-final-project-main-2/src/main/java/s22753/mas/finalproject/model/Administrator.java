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
public class Administrator extends Employee {
    @Min(0)
    private Double workingHours;

    public void setWorkingHours(Double weeklyHours)  {
        if(this.workingHours==null){
            this.workingHours = weeklyHours;
        }
        if (weeklyHours < this.workingHours){
            throw new IllegalArgumentException(String.format("The amount of hours (%s) cannot be decreased",getWorkingHours()));
        }
        this.workingHours = weeklyHours;
    }

    @Min(10)
    private Double hourlyRate;

    @NotBlank
    private String position;

    @Override
    Double yearlyIncome() {
        return hourlyRate*workingHours*52;
    }

    @OneToOne //dynamic
    Employee employee;

    @ManyToMany
    @JoinTable( name = "admin_management",
            joinColumns = {@JoinColumn(name = "administrator_id",foreignKey = @ForeignKey(name = "fk_management_admin"))},
            inverseJoinColumns = {@JoinColumn(name = "managementTask_id",foreignKey = @ForeignKey(name = "fk_admin_management"))}
    )
    @Setter(AccessLevel.NONE)
    private Set<ManagementTask> managementTasks;

    @ManyToMany
    @JoinTable( name = "admin_it",
            joinColumns = {@JoinColumn(name = "administrator_id",foreignKey = @ForeignKey(name = "fk_it_admin"))},
            inverseJoinColumns = {@JoinColumn(name = "itTask_id",foreignKey = @ForeignKey(name = "fk_admin_it"))}
    )
    private Set<ITtask> itTasks;


    public void addItTask(ITtask it){ //xor constraint
        if (it == null){
            throw new IllegalArgumentException("task cannot be null");
        }
        if (!getManagementTasks().isEmpty()){
            throw new IllegalArgumentException("IT tasks are not compatible with management tasks");
        }
        if (this.itTasks.contains(it)){
            return;
        }
        this.itTasks.add(it);
    }
    public void addManagementTask(ManagementTask mt){ //xor constraint
        if (mt == null){
            throw new IllegalArgumentException("task cannot be null");
        }
        if (this.managementTasks.contains(mt)){
            return;
        }
        if (!getItTasks().isEmpty()){
            throw new IllegalArgumentException("IT tasks are not compatible with management tasks");
        }
        this.managementTasks.add(mt);
        mt.addAdministartor(this);
    }
    public void removeManagementTask(ManagementTask mt){
        if(mt == null){
            throw new IllegalArgumentException("task cannot be null");
        }
        if (!managementTasks.contains(mt)){
            return;
        }
        managementTasks.remove(mt);
        mt.removeAdministrator(this);
    }
    public void removeItTask(ITtask it){
        if(it == null){
            throw new IllegalArgumentException("task cannot be null");
        }
        if (!itTasks.contains(it)){
            return;
        }
        itTasks.remove(it);
        it.removeAdministrator(this);
    }
    public void clearItTasks(){
        itTasks.clear();
    }
    public void clearManagementTasks(){
        managementTasks.clear();
    }

    @Override
    String getInfo() { //polimorphic method

        return "Name: "+ this.getFirstName()+ " "+ this.getLastName()+
                " Yearly Income: "+ this.yearlyIncome()+ " Position: "+ this.getPosition();
    }
}
