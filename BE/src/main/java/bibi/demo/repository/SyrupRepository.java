package bibi.demo.repository;

import bibi.demo.domain.Syrup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyrupRepository extends CrudRepository<Syrup, Long> {

    public Syrup findSyrupById(Long id);
}
