package ru.skillfactory;

import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

/**
 * @author Egor Voronianskii
 * @version 1.0
 */
public class AppTest {

    @Test
    public void whenWeTryGetWordsAndSpacesShouldCheckThatIsCorrect() throws Exception {
        App app = new App();
        Path path = Paths.get("/Users/vrnsky/Desktop/skillfactory.txt");
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>(Files.readAllLines(path));
        app.start(copyOnWriteArrayList, 100000);
        assertEquals(4, app.getSpaces());
        assertEquals(5, app.getWords());
        ExcelReporter excelReporter = new ExcelReporter();

        excelReporter.writeToFile(app.getWords(), app.getSpaces());
    }
}