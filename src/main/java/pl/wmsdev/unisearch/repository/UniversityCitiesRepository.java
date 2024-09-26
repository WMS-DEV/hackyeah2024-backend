package pl.wmsdev.unisearch.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import pl.wmsdev.unisearch.model.UniversityCities;

public interface UniversityCitiesRepository extends CrudRepository<UniversityCities, String> {
    @Query("SELECT city_name FROM university_cities WHERE university=:university")
    String getCityForUniversity(String university);
}
