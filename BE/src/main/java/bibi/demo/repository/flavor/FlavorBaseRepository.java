package bibi.demo.repository.flavor;

import bibi.demo.domain.flavor.FlavorBase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavorBaseRepository extends CrudRepository<FlavorBase, Long> {

    List<FlavorBase> findByBaseId(Long baseId);
}
