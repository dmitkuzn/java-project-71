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
    public void testJSONDifferPlain() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.json").toString(),
                                        getFixturePath("file2.json").toString(),
                                        "plain");
        String successResult = readFixture("success_plain.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testYAMLDifferPlain() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.yml").toString(),
                                        getFixturePath("file2.yml").toString(),
                                        "plain");
        String successResult = readFixture("success_plain.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDifferStylish() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.json").toString(),
                                        getFixturePath("file2.json").toString(),
                                        "stylish");
        String successResult = readFixture("success_stylish.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testYAMLDifferStylish() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.yml").toString(),
                                        getFixturePath("file2.yml").toString(),
                                        "stylish");
        String successResult = readFixture("success_stylish.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDifferJSON() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.json").toString(),
                                        getFixturePath("file2.json").toString(),
                                        "json");
        String successResult = readFixture("success_json.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testYAMLDifferJSON() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.yml").toString(),
                                        getFixturePath("file2.yml").toString(),
                                        "json");
        String successResult = readFixture("success_json.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDifferDefault() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString());
        String successResult = readFixture("success_stylish.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testYAMLDifferDefault() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.yml").toString(),
                getFixturePath("file2.yml").toString());
        String successResult = readFixture("success_stylish.txt");
        assertEquals(successResult, diff);
    }

/*
    @Test
    public void testJSONDifferPlain() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString(),
                "");
        String successResult = readFixture("success.txt");
        assertEquals(successResult, diff);
    }


    @Test
    public void testJSONDifferWithOutFormat() throws Exception {
        String diff = Differ.generate(getFixturePath("file1.json").toString(),
                getFixturePath("file2.json").toString());
        String successResult = readFixture("success.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDiffer2() throws Exception {
        String diff = Differ.generate(getFixturePath("file9_1.json").toString(),
                                        getFixturePath("file9_2.json").toString(),
                                        "");
        String successResult = readFixture("success_stylish.txt");
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
        String successResult = readFixture("success_plain.txt");
        assertEquals(successResult, diff);
    }

    @Test
    public void testJSONDiffer11Json() throws Exception {
        String diff = Differ.generate(getFixturePath("file9_1.json").toString(),
                getFixturePath("file9_2.json").toString(),
                "json");
        String successResult = readFixture("success_json.txt");
        assertEquals(successResult, diff);
    }*/
}
