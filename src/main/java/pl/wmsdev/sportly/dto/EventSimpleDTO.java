package pl.wmsdev.sportly.dto;

import lombok.Builder;
import pl.wmsdev.sportly.values.AgeGroup;
import pl.wmsdev.sportly.values.RequiredExperience;

@Builder
public record EventSimpleDTO(Long id, String name, CategoryDTO category, String description, Long startTime, Long endTime,
                             Double latitude, Double longitude, RequiredExperience requiredExperience, AgeGroup age, Integer calories) {
}
