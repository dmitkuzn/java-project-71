package hexlet.code;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class AppTest {

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        var path = getFixturePath(fileName);
        return Files.readString(path).trim();
    }

    @Test
    public void testJSONDiffer() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.json").toString(),
                                        getFixturePath("file2.json").toString(),
                                        "");
        String successResult = readFixture("success.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDiffer2() throws Exception {
        String diff = Differ.generate(getFixturePath("file9_1.json").toString(),
                                        getFixturePath("file9_2.json").toString(),
                                        "");
        String successResult = readFixture("success9.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testYAMLDiffer() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.yml").toString(),
                                        getFixturePath("file2.yml").toString(),
                                        "");
        String successResult = readFixture("success.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDiffer10Plain() throws Exception {
        String diff = Differ.generate(getFixturePath("file9_1.json").toString(),
                getFixturePath("file9_2.json").toString(),
                "plain");
        String successResult = readFixture("success10.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDiffer11Json() throws Exception {
        String diff = Differ.generate(getFixturePath("file9_1.json").toString(),
                getFixturePath("file9_2.json").toString(),
                "json");
        String successResult = readFixture("success11.txt");
        assertEquals(successResult, diff);
    }
}
