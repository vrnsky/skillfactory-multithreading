package ru.skillfactory;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Egor Voronianskii
 * @version 1.0
 */
public class ExcelReporter {

    public void writeToFile(int words, int spaces) throws IOException {
        final PrintableRow printableRow = new PrintableRow();
        printableRow.setWords(words);
        printableRow.setSpaces(spaces);

        Path reportFile = createFile("report");
        try (
                InputStream inputStream = Files.newInputStream(getTemplatePath());
                OutputStream outputStream = new BufferedOutputStream(Files.newOutputStream(reportFile))
                ) {
            XLSTransformer xlsTransformer = new XLSTransformer();
            Workbook workbook = xlsTransformer.transformXLS(inputStream, getMap(printableRow));
            workbook.write(outputStream);
            outputStream.flush();
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }

    }

    private Map<String, Object> getMap(PrintableRow printableRow) {
        Map<String, Object> map = new HashMap<>();
        map.put("rows", Collections.singletonList(printableRow));
        return map;
    }

    private Path createFile(String fileName) throws IOException {
        Path pathToReport = Paths.get("/Users/vrnsky/Desktop");
        Path fullPath = pathToReport.resolve(fileName + ".xlsx");

        if (Files.exists(fullPath)) {
            fullPath = fullPath.getParent().resolve(fullPath.getFileName().toString()
                    .replace(".xlsx", "") + System.currentTimeMillis() + ".xlsx");
        }

        Files.createFile(fullPath);
        return fullPath;
    }

    private Path getTemplatePath() {
        return Paths.get("/Users/vrnsky/Desktop/wordsspaces.xlsx");
    }
}
