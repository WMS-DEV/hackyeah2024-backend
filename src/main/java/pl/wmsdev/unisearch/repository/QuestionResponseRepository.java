package pl.wmsdev.unisearch.repository;

import org.springframework.data.repository.CrudRepository;
import pl.wmsdev.unisearch.model.QuestionResponse;

public interface QuestionResponseRepository extends CrudRepository<QuestionResponse, Long> {
}
