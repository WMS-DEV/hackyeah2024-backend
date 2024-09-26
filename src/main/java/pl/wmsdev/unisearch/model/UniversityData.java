package pl.wmsdev.unisearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Builder
public class UniversityData {
    @Id
    private Long id;
    private String uniSystemId;
    private String category;
    private String university;
    private String level;
    private String profile;
    private String classification;
    private LocalDate creationDate;
    private Boolean isTeacherStudies;
    private Boolean isForeignLanguageStudies;
    private Boolean isSharedStudies;
    private String status;
    private String disciplines;
    private String major;
    private String form;
    private String title;
    private String studyLanguage;
    private LocalDate startDate;
    private Integer semesters;
    private Integer ects;
    private Boolean isDualStudies;
}
