package pl.wmsdev.sportly.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import pl.wmsdev.sportly.dto.ParticipantDTO;
import pl.wmsdev.sportly.model.Participant;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParticipantMapper {

	ParticipantDTO participantToParticipantDTO(Participant participant);
}
