package pl.wmsdev.unisearch.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wmsdev.unisearch.model.CityData;

public interface CityDataRepository extends CrudRepository<CityData, Long> {

    CityData findByName(String name);
}
