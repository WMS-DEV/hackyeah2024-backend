package pl.wmsdev.unisearch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.wmsdev.unisearch.model.PerspektywyData;
import pl.wmsdev.unisearch.repository.PerspektywyDataRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CSVPerspektywyDataImporter {

    @Autowired
    private PerspektywyDataRepository perspektywyDataRepository;

    @Test
    @Disabled
    void doImport() throws IOException {
        var lines = Files.readAllLines(Paths.get("/home/michal/Documents/uni-perspektywy-cp.csv"));
        List<PerspektywyData> list = new ArrayList<>();
        System.out.println(lines.size());
        for(int i = 1; i < lines.size(); i++) {
            try {
                list.add(parseCity(lines.get(i).split(";")));
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
        System.out.println(list.size());
        perspektywyDataRepository.saveAll(list);
    }

    private PerspektywyData parseCity(String[] s) {
        return PerspektywyData.builder()
                .name(s[0])
                .place2023(s[1])
                .place2022(s[2])
                .place2021(s[3])
                .points(s[4] == null || s[4].isEmpty()  ? null : Double.parseDouble(s[4]))
                .rankingType(s[5])
                .build();
    }

}
