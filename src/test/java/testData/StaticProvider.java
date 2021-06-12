package testData;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.containers.LoginTestDataContainer;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StaticProvider {
    /**
     * @param m - method as a parameter that provide file name
     * @return - object of data
     */
    @SuppressWarnings("unchecked")
    @SneakyThrows
    @DataProvider(name = "loginWithIncorrectData")
    public static Object[][] loginWithIncorrectData(Method m) {

//   Set the data from .csv file to List
        List<LoginTestDataContainer> loginTestData =
                readFromCsv(m.getAnnotation(Test.class).groups()[0],
                        LoginTestDataContainer.class);


        LoginTestDataContainer[][] loginTestDataContainers = new LoginTestDataContainer[loginTestData.size()][];

        /**
         * Set the parsed from .csv login test data to object
         */
        for (int i = 0; i < loginTestData.size(); i++) {
            loginTestDataContainers[i] = new LoginTestDataContainer[]{loginTestData.get(i)};
        }
        return loginTestDataContainers;
    }

    /**
     *
     * @param filename - name of .csv file with data
     * @param classToDeserializeTo - model class contains fields for csv. file
     * @return - list of objects data to deserialize to from .csv file
     */
    @SuppressWarnings("unchecked")
    @SneakyThrows
    public static <T> List<T> readFromCsv(String filename, Class<T> classToDeserializeTo) {
        return new CsvToBeanBuilder(Files
                .newBufferedReader(Paths.get(ClassLoader.getSystemResource(filename).toURI())))
                .withType(classToDeserializeTo)
                .build()
                .parse();
    }
}