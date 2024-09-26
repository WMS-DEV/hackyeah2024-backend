package pl.wmsdev.unisearch.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class University extends UniversityData {

    private String cityName;
    private int monthlyFoodCostPLN;
    private int monthlyRoomRentalCostPLN;
    private int monthlyEntertainmentCostPLN;

    private Double medianMonthlySalaryInTheFirstGraduationYear;
    private Double salaryInRelation;
    private Double earnedMonthlySalaryInTheFirstGraduationYearToAverageInLivingArea;
    private Double timeOfLookingForAJobInMonths;
    private Double unemployedTimePercentageOfTheFirstYear;
    private Double unemployedTimePercentageOfTheFirstYearInRelationToAverageInLivingArea;

    private String rankingPlace2023;
    private String rankingPlace2022;
    private String rankingPlace2021;
    private Double rankingPoints;
    private String rankingType;

    private String erasmusKeyAction;
    private String erasmusActionType;
    private Integer erasmusFundingYear;
    private String erasmusProjectLink;

    public University(UniversityData universityData) {
        super(universityData.getId(), universityData.getUniSystemId(), universityData.getCategory(), universityData.getUniversity(),
                universityData.getLevel(), universityData.getProfile(), universityData.getClassification(), universityData.getCreationDate(),
                universityData.getIsTeacherStudies(), universityData.getIsForeignLanguageStudies(), universityData.getIsSharedStudies(),
                universityData.getStatus(), universityData.getDisciplines(), universityData.getMajor(), universityData.getForm(),
                universityData.getTitle(), universityData.getStudyLanguage(), universityData.getStartDate(), universityData.getSemesters(),
                universityData.getEcts(), universityData.getIsDualStudies());
    }

    public University(UniversityData universityData, CityData cityData) {
        super(universityData.getId(), universityData.getUniSystemId(), universityData.getCategory(), universityData.getUniversity(),
                universityData.getLevel(), universityData.getProfile(), universityData.getClassification(), universityData.getCreationDate(),
                universityData.getIsTeacherStudies(), universityData.getIsForeignLanguageStudies(), universityData.getIsSharedStudies(),
                universityData.getStatus(), universityData.getDisciplines(), universityData.getMajor(), universityData.getForm(),
                universityData.getTitle(), universityData.getStudyLanguage(), universityData.getStartDate(), universityData.getSemesters(),
                universityData.getEcts(), universityData.getIsDualStudies());

        cityName = cityData.getName();
        monthlyFoodCostPLN = cityData.getFoodCost();
        monthlyRoomRentalCostPLN = cityData.getRentalCost();
        monthlyEntertainmentCostPLN = cityData.getEntertainmentCost();
    }

    public University(UniversityData universityData, CityData cityData, UniSalaryData uniSalaryData) {
        this(universityData, cityData);

        medianMonthlySalaryInTheFirstGraduationYear = uniSalaryData.getTotalSalary();
        earnedMonthlySalaryInTheFirstGraduationYearToAverageInLivingArea = uniSalaryData.getSalaryInRelation();
        timeOfLookingForAJobInMonths = uniSalaryData.getTimeOfLookingForJob();
        unemployedTimePercentageOfTheFirstYear = uniSalaryData.getUnemployedRisk();
        unemployedTimePercentageOfTheFirstYearInRelationToAverageInLivingArea = uniSalaryData.getUnemployedRiskInRelation();
    }

    public University(UniversityData universityData, CityData cityData, UniSalaryData uniSalaryData, PerspektywyData perspektywyData) {
        this(universityData, cityData, uniSalaryData);

        rankingPlace2023 = perspektywyData.getPlace2023();
        rankingPlace2022 = perspektywyData.getPlace2022();
        rankingPlace2021 = perspektywyData.getPlace2021();
        rankingPoints = perspektywyData.getPoints();
        rankingType = perspektywyData.getRankingType();
    }

}
