package bibi.demo.repository.type;

import bibi.demo.domain.type.BaseType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseTypeRepository extends CrudRepository<BaseType, Long> {

    @Query(value = "SELECT bt.id FROM base_type bt WHERE bt.name_kr = :nameKR", nativeQuery = true)
    Optional<Long> findIdByNameKR(@Param("nameKR") String nameKR);
}
