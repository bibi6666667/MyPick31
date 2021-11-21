package bibi.demo.repository.flavor;

import bibi.demo.domain.flavor.FlavorSyrup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlavorSyrupRepository extends CrudRepository<FlavorSyrup, Long> {

    List<FlavorSyrup> findBySyrupId(Long syrupId);
}
