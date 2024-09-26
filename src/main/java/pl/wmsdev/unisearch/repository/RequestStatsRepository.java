package pl.wmsdev.unisearch.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wmsdev.unisearch.entity.RequestStats;

public interface RequestStatsRepository extends CrudRepository<RequestStats, Long> {
}
