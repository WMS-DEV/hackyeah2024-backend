package pl.wmsdev.unisearch.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wmsdev.unisearch.model.PerspektywyData;

import java.util.List;
import java.util.Optional;

public interface PerspektywyDataRepository extends CrudRepository<PerspektywyData, Long> {

    List<PerspektywyData> findByNameEqualsIgnoreCase(String name);
}
