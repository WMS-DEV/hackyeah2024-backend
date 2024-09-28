package pl.wmsdev.unisearch.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pl.wmsdev.unisearch.dto.CategoryDTO;
import pl.wmsdev.unisearch.model.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
	CategoryDTO categoryToCategoryDTO(Category category);
}
