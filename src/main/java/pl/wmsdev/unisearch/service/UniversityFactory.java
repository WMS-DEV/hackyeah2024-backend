package pl.wmsdev.unisearch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;
import org.springframework.stereotype.Service;
import pl.wmsdev.unisearch.model.*;
import pl.wmsdev.unisearch.repository.*;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UniversityFactory {
    private final UniversityCitiesRepository universityCitiesRepository;
    private final UniversityDataRepository universityDataRepository;
    private final UniSalaryDataRepository uniSalaryDataRepository;
    private final CityDataRepository cityDataRepository;
    private final PerspektywyDataRepository perspektywyDataRepository;
    private final ErasmusRepository erasmusRepository;

    public University getUniversityData(String uniName, String major) {
        UniversityData universityData = universityDataRepository.findByUniversityAndMajor(uniName, major);

        return new University(
                universityData,
                cityDataRepository.findByName(universityCitiesRepository.getCityForUniversity(uniName))
        );
    }

    public University getUniversity(UniversityData universityData) {
        CityData cityData = cityDataRepository.findByName(universityCitiesRepository.getCityForUniversity(universityData.getUniversity()));
        University university = new University(universityData);

        if(cityData != null) {
            university.setCityName(cityData.getName());
            university.setMonthlyFoodCostPLN(cityData.getFoodCost());
            university.setMonthlyRoomRentalCostPLN(cityData.getRentalCost());
            university.setMonthlyEntertainmentCostPLN(cityData.getEntertainmentCost());
        }

        List<UniSalaryData> uniSalaryData = getUniSalaryData(universityData);
        if(!uniSalaryData.isEmpty()) {
            UniSalaryData salaryData = uniSalaryData.get(0);
            university.setMedianMonthlySalaryInTheFirstGraduationYear(salaryData.getTotalSalary());
            university.setEarnedMonthlySalaryInTheFirstGraduationYearToAverageInLivingArea(salaryData.getSalaryInRelation());
            university.setTimeOfLookingForAJobInMonths(salaryData.getTimeOfLookingForJob());
            university.setUnemployedTimePercentageOfTheFirstYear(salaryData.getUnemployedRisk());
            university.setUnemployedTimePercentageOfTheFirstYearInRelationToAverageInLivingArea(salaryData.getUnemployedRiskInRelation());
        }

        List<PerspektywyData> perspektywyData = getPerspektywyData(universityData);
        if(!perspektywyData.isEmpty()) {
            PerspektywyData perspektywy = perspektywyData.get(0);
            university.setRankingPlace2023(perspektywy.getPlace2023());
            university.setRankingPlace2022(perspektywy.getPlace2022());
            university.setRankingPlace2021(perspektywy.getPlace2021());
            university.setRankingPoints(perspektywy.getPoints());
            university.setRankingType(perspektywy.getRankingType());
        }

        List<Erasmus> erasmusData = getErasmusData(universityData);
        if(!erasmusData.isEmpty()) {
            Erasmus erasmus = erasmusData.get(0);
            university.setErasmusKeyAction(erasmus.getKeyAction());
            university.setErasmusActionType(erasmus.getActionType());
            university.setErasmusFundingYear(erasmus.getFundingYear());
            university.setErasmusProjectLink(erasmus.getProjectLink());
        }
//        if (uniSalaryData.isEmpty() && perspektywyData.isEmpty()) {
//            return new University(universityData, cityData);
//        } else if(perspektywyData.isEmpty()) {
//            return new University(universityData, cityData, uniSalaryData.get(0));
//        } else {
//            return new University(universityData, cityData, uniSalaryData.get(0), perspektywyData.get());
//        }

        return university;
    }

    private List<UniSalaryData> getUniSalaryData(UniversityData universityData) {
        String level = universityData.getForm().equals("magister") ? "II stopnia" : "I stopnia";
        return uniSalaryDataRepository.findByInstitutionEqualsIgnoreCaseAndNameEqualsIgnoreCaseAndStudyFormEqualsIgnoreCaseAndStudyLevelEqualsIgnoreCase(
                universityData.getUniversity(), universityData.getMajor(), universityData.getForm(), level, Pageable.ofSize(1).withPage(0));
    }

    private List<PerspektywyData> getPerspektywyData(UniversityData universityData) {
        return perspektywyDataRepository.findByNameEqualsIgnoreCase(universityData.getUniversity());
    }

    private List<Erasmus> getErasmusData(UniversityData universityData) {
        return erasmusRepository.findByUniversityLikeIgnoreCase(universityData.getUniversity(), Pageable.ofSize(1).withPage(0));
    }
}
