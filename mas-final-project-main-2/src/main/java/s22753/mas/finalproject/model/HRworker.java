package s22753.mas.finalproject.model;
import javax.persistence.*;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class HRworker extends Employee {
    @Min(500)
    private Double salary;

    private Double bonus; //optional

    @Min(0)
    private int performanceRating;

    @OneToOne
    Employee employee;

    @Override
    Double yearlyIncome(){
        if (bonus==null){
            return salary*12;
        }else {
            return (salary * 12) + bonus;
        }
    }
    @Override
    String getInfo() { //polimorphic method

        return "Name: "+ this.getFirstName()+ " "+ this.getLastName()+ " Yearly income: "+ this.yearlyIncome()+
                " Performance rating: "+ this.getPerformanceRating();
    }



}
