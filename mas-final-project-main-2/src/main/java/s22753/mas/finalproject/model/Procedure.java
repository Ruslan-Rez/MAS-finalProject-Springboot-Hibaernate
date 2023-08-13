package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Procedure {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Name is mandatory")
  @Size(min = 2,max = 255)
  private String name;

  @Min(0)
  private Double price;


  @ElementCollection //multivalue attribute
  @CollectionTable(name = "medication", joinColumns = @JoinColumn(name = "procedure_id"))
  @Builder.Default
  private Set<String> medication = new HashSet<>();


  @OneToMany(mappedBy = "procedure",cascade = {CascadeType.REMOVE}) //association with an attribute
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private Set<Visit>visits;

  @ManyToOne(fetch = FetchType.LAZY, optional = false) //complex attribute
  @JoinColumn(name = "procedureDetails_id", foreignKey = @ForeignKey(name = "fk_procedureDetails"))
  private ProcedureDetails procedureDetails;

  @ManyToMany(mappedBy = "procedures") //basic
  private Set<Doctor> doctors;
}
