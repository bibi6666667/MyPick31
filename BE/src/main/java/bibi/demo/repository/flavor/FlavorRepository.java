package bibi.demo.repository.flavor;

import bibi.demo.domain.flavor.Flavor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlavorRepository extends CrudRepository<Flavor, Long> {

    @Override
    List<Flavor> findAll();

    List<Flavor> findByNameKRContaining(String nameKR);

    List<Flavor> findByNameENContainingIgnoreCase(String nameEN);

    List<Flavor> findAll(Sort sort);

    @Override
    Optional<Flavor> findById(Long id);

    List<Flavor> findByIsSignatureTrue();

    @Query(value = "SELECT `flavor`.`id`, `flavor`.`name_kr`, `flavor`.`name_en`, `flavor`.`kcal`, `flavor`.`is_signature`, `flavor`.`image_id`, `flavor`.`info_id`, `flavor`.`on_sale_id`" +
            " FROM `flavor` LEFT JOIN `on_sale` ON `flavor`.`on_sale_id` = `on_sale`.`id` WHERE `on_sale`.`is_on_sale` = true", nativeQuery = true)
    List<Flavor> findByOnSaleTrue();
}
