package bibi.demo.repository.type;

import bibi.demo.domain.type.ToppingType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToppingTypeRepository extends CrudRepository<ToppingType, Long> {

    @Query(value = "SELECT tt.id FROM topping_type tt WHERE tt.name_kr = :nameKR", nativeQuery = true)
    Optional<Long> findIdByNameKR(@Param("nameKR") String nameKR);
}
