package pl.wmsdev.unisearch.model;

import lombok.Builder;

@Builder
public record UniversityResult(String name, String fieldOfStudy) {
}
