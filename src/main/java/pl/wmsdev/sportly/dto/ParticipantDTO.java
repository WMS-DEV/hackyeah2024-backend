package pl.wmsdev.sportly.dto;

import java.util.Set;

public record ParticipantDTO(Long id, String name, String email, Set<EventSimpleDTO> events) {
}
