package bibi.demo.repository;

import bibi.demo.domain.Allergen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenRepository extends CrudRepository<Allergen, Long> {

    public Allergen findAllergenById(Long id);
}
