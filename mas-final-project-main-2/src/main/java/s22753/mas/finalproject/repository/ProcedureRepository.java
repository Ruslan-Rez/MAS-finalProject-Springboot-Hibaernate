package s22753.mas.finalproject.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import s22753.mas.finalproject.model.Procedure;

import java.util.List;

public interface ProcedureRepository extends CrudRepository<Procedure,Long> {
    public List<Procedure> findByName(String name);
    public List<Procedure> findByNameAndPrice(String name, Double price);
    @Query("from Procedure as p where p.price > :minPrice")
    public List<Procedure>findProcedureWithPriceGreaterThan(@Param("minPrice")double minPrice);
}
