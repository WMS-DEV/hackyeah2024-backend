package pl.wmsdev.sportly.dto;

import lombok.Builder;
import pl.wmsdev.sportly.values.AgeGroup;
import pl.wmsdev.sportly.values.Cycle;
import pl.wmsdev.sportly.values.RequiredExperience;

import java.util.List;

@Builder
public record EventRequest(String name, Long categoryId, Long creatorId, String description, Long startTime, Long endTime,
                           Cycle cyclic, Integer maxNumberOfParticipants, List<String> inviteEmails,
                           Boolean isPublic, RequiredExperience requiredExperience, AgeGroup age, Double longitude, Double latitude) {
}
