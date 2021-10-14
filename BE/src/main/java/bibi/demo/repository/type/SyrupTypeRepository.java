package bibi.demo.repository.type;

import bibi.demo.domain.type.SyrupType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SyrupTypeRepository extends CrudRepository<SyrupType, Long> {

    @Query(value = "SELECT st.id FROM syrup_type st WHERE st.name_kr = :nameKR", nativeQuery = true)
    Optional<Long> findIdByNameKR(@Param("nameKR") String nameKR);
}
