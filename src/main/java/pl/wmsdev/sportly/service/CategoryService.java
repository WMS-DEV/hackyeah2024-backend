package pl.wmsdev.sportly.service;

import pl.wmsdev.sportly.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

	List<CategoryDTO> getCategories();
}
