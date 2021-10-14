package bibi.demo.repository;

import bibi.demo.domain.Topping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToppingRepository extends CrudRepository<Topping, Long> {

    @Override
    Optional<Topping> findById(Long id);

    List<Topping> findByToppingTypeId(Long toppingTypeId);
}
