package bibi.demo.repository;

import bibi.demo.domain.flavor.FlavorBase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavorBaseRepository extends CrudRepository<FlavorBase, Long> {

    @Query(value = "SELECT fb.id, fb.flavor_id, fb.base_id FROM flavor_base fb WHERE fb.base_id = :baseId", nativeQuery = true)
    List<FlavorBase> findFlavorBasesByBaseId(@Param("baseId") Long baseId);
}
