package bibi.demo.repository.flavor;

import bibi.demo.domain.Pick;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PickRepository extends CrudRepository<Pick, Long> {
    @Override
    <S extends Pick> S save(S entity);

    @Override
    void delete(Pick entity);

    @Override
    void deleteById(Long pickId);

    Long countByFlavorId(Long flavorId);


    List<Pick> findByUserId(Long userId);

    Optional<Pick> findByFlavorIdAndUserId(Long flavorId, Long userId);

    boolean existsByFlavorIdAndUserId(Long flavorId, Long userId);

    boolean existsById(Long pickId);
}
