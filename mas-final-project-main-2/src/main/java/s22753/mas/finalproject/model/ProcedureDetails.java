package s22753.mas.finalproject.model;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProcedureDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private boolean requiresAnesthesia;

    @NotNull
    private Duration procedureLength;

    @Min(0)
    private Duration recoveryLength;

    @OneToMany(mappedBy = "procedureDetails")
    private Set<Procedure> procedures;
}
