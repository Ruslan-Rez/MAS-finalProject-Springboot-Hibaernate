package s22753.mas.finalproject.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ward {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String roomNumber;

    @Min(1)
    private int capacity;

    @OneToMany(mappedBy = "ward")
    @MapKey(name = "id") //qualified
    private Map<Long,Visit> visits;
}
