package s22753.mas.finalproject.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ITtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String serverAddress;


    @ManyToMany(mappedBy = "itTasks") //xor
    private Set<Administrator> administrators;

    public void addAdministartor(Administrator a){
        if (a == null){
            throw new IllegalArgumentException("Admin cannot be null");
        }
        if (this.administrators.contains(a)){
            return;
        }
        this.administrators.add(a);
        a.addItTask(this);
    }
    public void removeAdministrator(Administrator a){
        if(a == null){
            throw new IllegalArgumentException("Admin cannot be null");
        }
        if (!administrators.contains(a)){
            return;
        }
        administrators.remove(a);
        a.removeItTask(this);
    }



}
