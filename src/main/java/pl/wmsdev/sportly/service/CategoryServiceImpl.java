package pl.wmsdev.sportly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wmsdev.sportly.dto.CategoryDTO;
import pl.wmsdev.sportly.mapper.CategoryMapper;
import pl.wmsdev.sportly.model.Category;
import pl.wmsdev.sportly.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;

	@Override
	public List<CategoryDTO> getCategories() {
		return categoryRepository.findAll().stream()
				.map(categoryMapper::categoryToCategoryDTO)
				.toList();
	}

	@Override
	public Optional<Category> findCategoryById(Long id) {
		return categoryRepository.findById(id);
	}
}
