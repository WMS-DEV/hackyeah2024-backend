package pl.wmsdev.sportly.dto;

import lombok.Builder;
import pl.wmsdev.sportly.values.AgeGroup;
import pl.wmsdev.sportly.values.Cycle;
import pl.wmsdev.sportly.values.RequiredExperience;

import java.util.List;

@Builder
public record EventRequest(String name, Long categoryId, String description, Long startTime, Long endTime,
                           Long creatorId, String creatorEmail, // ONLY ONE NECESSARY
                           Cycle cyclic, Integer maxNumberOfParticipants, List<String> inviteEmails,
                           Boolean isPublic, RequiredExperience requiredExperience, AgeGroup age, Double longitude, Double latitude) {
}
