package bibi.demo.repository;

import bibi.demo.domain.Allergen;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AllergenRepository extends CrudRepository<Allergen, Long> {

    @Override
    Optional<Allergen> findById(Long id);
}
