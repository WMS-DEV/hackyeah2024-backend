package pl.wmsdev.unisearch.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wmsdev.unisearch.model.UniSalaryData;
import pl.wmsdev.unisearch.model.UniversityData;

import java.util.List;
import java.util.Optional;

public interface UniSalaryDataRepository extends PagingAndSortingRepository<UniSalaryData, Long> {

    List<UniSalaryData> findByInstitutionEqualsIgnoreCaseAndNameEqualsIgnoreCaseAndStudyFormEqualsIgnoreCaseAndStudyLevelEqualsIgnoreCase(String institution, String name, String studyForm, String studyLevel, Pageable pageable);
}
