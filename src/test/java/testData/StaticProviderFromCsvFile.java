package testData;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import models.User;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StaticProviderFromCsvFile {
//
//    @SneakyThrows
//    @DataProvider(name = "userDetails")
//    public static Object[][] readCsv() {
//        CSVReader csvReader = new CSVReader(Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("users" +
//                ".csv").toURI())));
//        List<String[]> csvData = csvReader.readAll();
//        Object[][] csvDataObject = new Object[csvData.size()][];
//        for (int i = 0; i < csvData.size(); i++) {
//            csvDataObject[i] = csvData.get(i);
//        }
//        csvReader.close();
//        return csvDataObject;
//        }

//    @SneakyThrows
//    @DataProvider(name = "readFromCsvFile")
//    public static Object[][] readCsv(Method m) {
//
//        Object[][] csvDataObject = null;
//
//        for (String fileName : ((Test) m.getAnnotation(Test.class)).groups()) {
//            CSVReader csvReader =
//                    new CSVReader(Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource(fileName).toURI()
//                    )));
//            List<String[]> csvData = csvReader.readAll();
//            csvDataObject = new Object[csvData.size()][];
//            for (int i = 0; i < csvData.size(); i++) {
//                csvDataObject[i] = csvData.get(i);
//            }
//            csvReader.close();
//        }
//        return csvDataObject;
//    }


    @SuppressWarnings("unchecked")
    @SneakyThrows
    @DataProvider(name = "readFromCsvFile")
    public static Object[][] readCsv(Method m) {

        CSVReader csvReader =
                    new CSVReader(Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("users.csv").toURI()
                    )));
        List<User> beans = new CsvToBeanBuilder<User>(csvReader)
                .withType(User.class)
                .build()
                .parse();

        beans.forEach(System.out::println);
//        User[][] csvDataObject = new User[beans.size()][];
//        for (int i = 0; i < beans.size(); i++) {
//            csvDataObject[i] = beans.get(i);
//        }
//        return csvDataObject;
        return null;
    }

}
