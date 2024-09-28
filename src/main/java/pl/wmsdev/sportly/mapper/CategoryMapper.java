package pl.wmsdev.sportly.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pl.wmsdev.sportly.dto.CategoryDTO;
import pl.wmsdev.sportly.model.Category;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper {
	CategoryDTO categoryToCategoryDTO(Category category);
}
