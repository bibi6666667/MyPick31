package bibi.demo.repository;

import bibi.demo.domain.Base;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends CrudRepository<Base, Long> {

    public Base findBaseById(Long id);
}
