package pl.wmsdev.unisearch.service;

import pl.wmsdev.unisearch.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

	List<CategoryDTO> getCategories();
}
