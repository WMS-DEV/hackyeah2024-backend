package pl.wmsdev.unisearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wmsdev.unisearch.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
