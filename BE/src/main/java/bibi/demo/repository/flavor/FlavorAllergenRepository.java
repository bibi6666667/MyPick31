package bibi.demo.repository.flavor;

import bibi.demo.domain.flavor.FlavorAllergen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavorAllergenRepository extends CrudRepository<FlavorAllergen, Long> {

    List<FlavorAllergen> findByAllergenId(Long allergenId);
}
