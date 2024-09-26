package pl.wmsdev.unisearch.dto;

import pl.wmsdev.unisearch.model.University;

import java.util.List;

public record UniSearchResponse(List<University> suggestedUniversities, String generalDescription) {
}
