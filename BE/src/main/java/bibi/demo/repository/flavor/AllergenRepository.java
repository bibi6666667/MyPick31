package bibi.demo.repository.flavor;

import bibi.demo.domain.Allergen;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllergenRepository extends CrudRepository<Allergen, Long> {

    @Override
    Optional<Allergen> findById(Long id);

    @Query(value = "SELECT a.id FROM allergen a WHERE a.name_kr = :nameKR", nativeQuery = true)
    Optional<Long> findIdByNameKR(@Param("nameKR") String nameKR);
}
