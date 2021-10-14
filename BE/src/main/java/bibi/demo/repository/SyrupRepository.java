package bibi.demo.repository;

import bibi.demo.domain.Syrup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyrupRepository extends CrudRepository<Syrup, Long> {

    @Override
    Optional<Syrup> findById(Long id);

    List<Syrup> findBySyrupTypeId(Long syrupTypeId);
}
