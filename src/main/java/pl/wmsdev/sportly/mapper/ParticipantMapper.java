package pl.wmsdev.sportly.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import pl.wmsdev.sportly.dto.CategoryDTO;
import pl.wmsdev.sportly.dto.EventSimpleDTO;
import pl.wmsdev.sportly.dto.ParticipantDTO;
import pl.wmsdev.sportly.dto.ParticipantSimpleDTO;
import pl.wmsdev.sportly.model.Event;
import pl.wmsdev.sportly.model.Participant;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParticipantMapper {

	ParticipantSimpleDTO participantToParticipantSimpleDTO(Participant participant);

	@Mapping(source = "events", target = "events", qualifiedByName = "eventToEventSimpleDTO")
	ParticipantDTO participantToParticipantWithEventsDTO(Participant participant);

	@Named("eventToEventSimpleDTO")
	default Set<EventSimpleDTO> eventToSimpleDTO(Set<Event> events) {
		return events.stream()
				.map(event -> EventSimpleDTO.builder()
						.id(event.getId())
						.name(event.getName())
						.category(new CategoryDTO(event.getCategory().getId(), event.getCategory().getName()))
						.description(event.getDescription())
						.startTime(event.getStartTime())
						.endTime(event.getEndTime())
						.latitude(event.getLatitude())
						.longitude(event.getLongitude())
						.requiredExperience(event.getRequiredExperience())
						.age(event.getAgeGroup())
						.calories(event.getCalories())
						.build())
				.collect(Collectors.toSet());
	}
}
