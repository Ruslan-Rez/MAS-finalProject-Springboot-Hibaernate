package s22753.mas.finalproject.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import jdk.jfr.Enabled;
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
public abstract class Person { //abstract class
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank //polimorphic method
    abstract String getInfo();
}
