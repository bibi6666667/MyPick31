package bibi.demo.repository;

import bibi.demo.domain.flavor.FlavorTopping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavorToppingRepository extends CrudRepository<FlavorTopping, Long> {

    List<FlavorTopping> findByToppingId(Long toppingId);
}
