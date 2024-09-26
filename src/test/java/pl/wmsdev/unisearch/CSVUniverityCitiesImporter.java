package pl.wmsdev.unisearch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wmsdev.unisearch.model.UniversityCities;
import pl.wmsdev.unisearch.repository.UniversityCitiesRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CSVUniverityCitiesImporter {

    @Autowired
    private UniversityCitiesRepository citiesRepository;

    @Test
    @Disabled
    void doImport() throws IOException {
        var lines = Files.readAllLines(Paths.get("/home/michal/Documents/university_cities.csv"));
        List<UniversityCities> list = new ArrayList<>();
        System.out.println(lines.size());
        for(int i = 1; i < lines.size(); i++) {
            try {
                list.add(parseUniversity(lines.get(i).split("@")));
            } catch (Throwable t) {
                System.out.println(lines.get(i));
                t.printStackTrace();
            }
        }
        System.out.println(list.size());
        citiesRepository.saveAll(list);
    }

    private UniversityCities parseUniversity(String[] s) {
        return UniversityCities.builder()
                .university(s[0])
                .cityName(s[1])
                .isPublic(!s[2].equals("0"))
                .logoLink(s[3])
                .build();
    }
}
