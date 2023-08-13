package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Department {

    @NotBlank
    private String deptName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "dept",fetch = FetchType.LAZY) //basic
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Doctor> members = new HashSet<>();
}
