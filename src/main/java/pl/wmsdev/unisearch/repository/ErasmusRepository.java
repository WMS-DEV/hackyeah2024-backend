package pl.wmsdev.unisearch.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.wmsdev.unisearch.model.Erasmus;

import java.util.List;
import java.util.Optional;

public interface ErasmusRepository extends PagingAndSortingRepository<Erasmus, Long> {

    List<Erasmus> findByUniversityLikeIgnoreCase(String university, Pageable pageable);
}
