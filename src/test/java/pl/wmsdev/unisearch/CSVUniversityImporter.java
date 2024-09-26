package pl.wmsdev.unisearch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wmsdev.unisearch.model.UniversityData;
import pl.wmsdev.unisearch.repository.UniversityDataRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CSVUniversityImporter {

    private final DateTimeFormatter CSV_FORMMATER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Autowired
    private UniversityDataRepository dataRepository;

    @Test
    @Disabled
    void doImport() throws IOException {
        var lines = Files.readAllLines(Paths.get("C:\\Users\\micha\\IdeaProjects\\wmsdev-hackyeah-backend-starter-no-auth\\src\\test\\resources\\merged-michal.csv"));
        List<UniversityData> list = new ArrayList<>();
        System.out.println(lines.size());
        for(int i = 1; i < lines.size(); i++) {
            try {
                list.add(parseUniversiry(lines.get(i).split(";")));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        System.out.println(list.size());
//        dataRepository.saveAll(list.stream().limit(10).toList());
        dataRepository.saveAll(list);
    }

    private UniversityData parseUniversiry(String[] s) {
        return UniversityData.builder()
                .uniSystemId(s[0])
                .category(s[1])
                .university(s[2])
                .level(s[3])
                .profile(s[4])
                .classification(s[6])
                .creationDate(LocalDate.parse(s[7], CSV_FORMMATER))
                .isTeacherStudies(parseBoolean(s[8]))
                .isForeignLanguageStudies(parseBoolean(s[9]))
                .isSharedStudies(parseBoolean(s[11]))
                .status(s[12])
                .disciplines(s[15])
                .major(s[24])
                .form(s[25])
                .title(s[26])
                .studyLanguage(s[27])
                .startDate(LocalDate.parse(s[29], CSV_FORMMATER))
                .semesters(Integer.valueOf(s[30]))
                .ects(Integer.valueOf(s[31]))
                .isDualStudies(parseBoolean(s[32])).build();
    }

    private boolean parseBoolean(String s) {
        return s.equalsIgnoreCase("tak");
    }

}
