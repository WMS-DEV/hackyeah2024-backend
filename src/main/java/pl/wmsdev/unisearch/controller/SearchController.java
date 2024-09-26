package pl.wmsdev.unisearch.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import pl.wmsdev.unisearch.dto.*;
import pl.wmsdev.unisearch.service.UniSearchService;
import lombok.RequiredArgsConstructor;
import pl.wmsdev.unisearch.stats.StatsReporterService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/search")
@RequiredArgsConstructor
@RestController
public class SearchController {

    private final UniSearchService searchService;
    private final StatsReporterService reporterService;

    @PostMapping
    @Operation(summary = "Post final form results")
    public UniSearchResponse query(@RequestBody UniSearchRequest body, HttpServletRequest request) {
        return reporterService.report(request, body, () -> searchService.query(body));
    }
    @PostMapping("/mock")
    @Operation(summary = "Get random universities - use for testing purposes")
    @Deprecated
    public UniSearchResponse queryMock(UniSearchRequest body, HttpServletRequest request) {
        return reporterService.report(request, body, searchService::random);
    }

    @PostMapping("/suggest/profile")
    @Operation(summary = "Suggest profile (don't use in POC)")
    @Deprecated
    public List<String> suggestProfile(@RequestBody List<QuestionAndAnswer> questionAndAnswers, HttpServletRequest request) {
        return reporterService.report(request, questionAndAnswers, () -> searchService.suggestProfile(questionAndAnswers));
    }

    @PostMapping("/suggest/classification")
    @Operation(summary = "Suggest category / classification")
    public List<String> suggestClassification(@RequestBody List<QuestionAndAnswer> questionAndAnswers, HttpServletRequest request) {
        return reporterService.report(request, questionAndAnswers, () -> searchService.suggestClassification(questionAndAnswers));
    }

    @PostMapping("/suggest/major")
    @Operation(summary = "Suggest major, required information regarding selected classifications")
    public List<String> suggestMajor(@RequestBody SuggestMajorRequest body, HttpServletRequest request) {
        return reporterService.report(request, () -> searchService.suggestMajor(body.questionAndAnswers(), body.classifications(),
                body.studyLevels(), body.studyForms()), body.questionAndAnswers(), body.classifications(), body.studyLevels(),
                body.studyForms());
    }

    @PostMapping("/suggest/university")
    @Operation(summary = "Suggest universities - requires info about selected majors")
    public List<String> suggestUniversity(@RequestBody SuggestUniversityRequest body, HttpServletRequest request) {
        return reporterService.report(request, () -> searchService.suggestUniversity(body.questionAndAnswers(), body.majors()),
                body.questionAndAnswers(), body.majors());
    }

    @PostMapping("/describe")
    @Operation(summary = "Describe a university for the user based on the id")
    public String describeUniversity(@RequestBody List<QuestionAndAnswer> questionAndAnswers,
                                     @RequestParam String universityId, HttpServletRequest request) {
        return reporterService.report(request, () -> searchService.describe(universityId, questionAndAnswers),
                questionAndAnswers, universityId);
    }

}
