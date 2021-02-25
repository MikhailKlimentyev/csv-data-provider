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

    @DataProvider(name = "testData")
    private Iterator<Object[]> parseCsvData() throws IOException {
        BufferedReader input = null;
        File file = new File("src/main/resources/CsvData.csv");
        input = new BufferedReader(new FileReader(file));
        String line = null;
        List<Object[]> data = new ArrayList<>();
        for (int count = 0; (line = input.readLine()) != null; count++) {
            if (count != 0) {
                String in = line.trim();
                String[] temp = in.split(",");
                List<String> array = new ArrayList();
                for (String s : temp) {
                    array.add(s);
                }
                data.add(array.toArray());
            }
        }
        input.close();
        return data.iterator();
    }

    @Test(dataProvider = "testData")
    public void verifyLoginUsingCsv(String username, String password, String testDescription, String thirdColumn) {
        System.out.print(username + " " + password + " " + testDescription + thirdColumn + "\n");
    }
}
