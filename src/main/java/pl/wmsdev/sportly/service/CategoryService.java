package pl.wmsdev.sportly.service;

import pl.wmsdev.sportly.dto.CategoryDTO;
import pl.wmsdev.sportly.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

	List<CategoryDTO> getCategories();

	Optional<Category> findCategoryById(Long id);
}
