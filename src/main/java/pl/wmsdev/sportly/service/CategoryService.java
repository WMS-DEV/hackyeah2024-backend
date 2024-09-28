package pl.wmsdev.sportly.service;

import pl.wmsdev.sportly.dto.CategoryDTO;
import pl.wmsdev.sportly.model.Category;

import java.util.List;

public interface CategoryService {

	List<CategoryDTO> getCategories();

	Category getCategoryById(Long id);
}
