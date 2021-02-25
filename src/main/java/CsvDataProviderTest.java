import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvDataProviderTest {

    private Iterator<Object[]> parseCsvData(String fileName) throws IOException {
        BufferedReader input = null;
        File file = new File(fileName);
        input = new BufferedReader(new FileReader(file));
        String line = null;
        List<Object[]> data = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            String in = line.trim();
            String[] temp = in.split(",");
            List<String> array = new ArrayList();
            for (String s : temp) {
                array.add(s);
            }
            data.add(array.toArray());
        }
        input.close();
        return data.iterator();
    }

    @DataProvider(name = "testData")
    public Iterator<Object[]> testData() throws IOException {
        return parseCsvData("src/main/resources/CsvData.csv");
    }

    @Test(dataProvider = "testData")
    public void verifyLoginUsingCsv(String username, String password, String testDescription) {
        System.out.println(username);
        System.out.println(password);
        System.out.println(testDescription);
    }
}
