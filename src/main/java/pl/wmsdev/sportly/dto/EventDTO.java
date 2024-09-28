package pl.wmsdev.sportly.dto;

import pl.wmsdev.sportly.values.AgeGroup;
import pl.wmsdev.sportly.values.Cycle;
import pl.wmsdev.sportly.values.RequiredExperience;

import java.util.Set;

public record EventDTO(Long id, String name, ParticipantDTO createdBy, CategoryDTO category, String description,
                       Long startTime, Long endTime, Double latitude, Double longitude, Cycle cyclic, Integer maxNumberOfParticipants, Set<ParticipantDTO> participants,
                       Boolean isPublic, RequiredExperience requiredExperience, AgeGroup age, Long calories) {
}
