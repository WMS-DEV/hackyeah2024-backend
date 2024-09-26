package pl.wmsdev.unisearch.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class UniSalaryData {
    @Id
    private Long id;
    private String institution;
    private String name;
    private String studyLevel;
    private String studyForm;
    private String studyProfile;
    private Integer graduationYear;
    private Integer graduatesNumber;
    private Double totalSalary;
    private Double salaryInRelation;
    private Double timeOfLookingForJob;
    private Double unemployedRisk;
    private Double unemployedRiskInRelation;
}
