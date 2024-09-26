package pl.wmsdev.unisearch.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import pl.wmsdev.unisearch.model.UniversityData;

import java.util.List;
import java.util.Optional;

public interface UniversityDataRepository extends CrudRepository<UniversityData, Long> {

    @Query("SELECT DISTINCT(classification) FROM university_data")
    List<String> getDistinctClassifications();

    @Query("SELECT DISTINCT(major) FROM university_data WHERE classification IN(:classifications) AND level IN(:levels) AND form IN(:forms)")
    List<String> getDistinctMajorsForClassificationAndStudyForm(List<String> classifications, List<String> levels, List<String> forms);

    @Query("SELECT DISTINCT(university) FROM university_data WHERE major IN(:majors)")
    List<String> getDistinctUniversitiesByMajors(List<String> majors);

    UniversityData findByUniversityAndMajor(String university, String major);

    @Query("SELECT DISTINCT(major) FROM university_data WHERE classification=:classification AND level=:level")
    List<String> getDistinctMajorsForClassification(String classification, String level);

    List<UniversityData> findByMajorLikeIgnoreCaseAndUniversityLikeIgnoreCaseAndLevelIsIn(String major, String university, List<String> level);

    Optional<UniversityData> findByUniSystemId(String uniSystemId);
}
