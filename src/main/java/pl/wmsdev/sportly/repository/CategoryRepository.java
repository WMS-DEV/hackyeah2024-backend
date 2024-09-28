package pl.wmsdev.sportly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wmsdev.sportly.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
