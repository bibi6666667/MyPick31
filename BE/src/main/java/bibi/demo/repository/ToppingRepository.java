package bibi.demo.repository;

import bibi.demo.domain.Topping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToppingRepository extends CrudRepository<Topping, Long> {

    public Topping findToppingById(Long id);
}
