package pl.wmsdev.unisearch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wmsdev.unisearch.model.Erasmus;
import pl.wmsdev.unisearch.repository.ErasmusRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CSVErasmusDataImporter {
    @Autowired
    private ErasmusRepository erasmusRepository;

    @Test
//    @Disabled
    void doImport() throws IOException {
        var lines = Files.readAllLines(Paths.get("C:\\Users\\wojte\\IdeaProjects\\wmsdev-hackyeah-backend-2023\\src\\test\\resources\\erasmus-the-one.csv"));
        List<Erasmus> list = new ArrayList<>();
        System.out.println(lines.size());
        for(int i = 1; i < lines.size(); i++) {
            try {
                list.add(parseCity(lines.get(i).split(";")));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        System.out.println(list.size());
        erasmusRepository.saveAll(list);
    }

    private Erasmus parseCity(String[] s) {
        return Erasmus.builder()
                .keyAction(s[0])
                .actionType(s[1])
                .fundingYear(Integer.valueOf(s[2]))
                .projectLink(s[3])
                .university(s[4])
                .build();
    }
}
