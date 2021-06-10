package testData;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import testData.containers.LoginTestDataContainer;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StaticProviderFromCsvFile {
    @SuppressWarnings("unchecked")
    @SneakyThrows
    @DataProvider(name = "readFromCsvFile")
    public static Object[][] readCsv(Method m) {
        CSVReader csvReader = new CSVReader(Files.newBufferedReader(Paths.get(ClassLoader
                        .getSystemResource(m.getAnnotation(Test.class).groups()[0]).toURI())));
        List<LoginTestDataContainer> beans = new CsvToBeanBuilder(csvReader)
                .withType(LoginTestDataContainer.class)
                .build()
                .parse();

        LoginTestDataContainer[][] loginTestDataContainers = new LoginTestDataContainer[beans.size()][];

        for (int i = 0; i < beans.size(); i++) {
            loginTestDataContainers[i] = new LoginTestDataContainer[]{beans.get(i)};
        }
        return loginTestDataContainers;
    }
}
