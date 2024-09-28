package pl.wmsdev.sportly.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import pl.wmsdev.sportly.dto.EventDTO;
import pl.wmsdev.sportly.model.Event;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {

	@Mapping(source = "creator", target = "createdBy")
	@Mapping(source = "maxParticipants", target = "maxNumberOfParticipants")
	@Mapping(source = "ageGroup", target = "age")
	EventDTO eventToEventDTO(Event event);


}
