package pl.wmsdev.sportly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wmsdev.sportly.dto.CategoryDTO;
import pl.wmsdev.sportly.mapper.CategoryMapper;
import pl.wmsdev.sportly.repository.CategoryRepository;

import java.util.List;

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
}
