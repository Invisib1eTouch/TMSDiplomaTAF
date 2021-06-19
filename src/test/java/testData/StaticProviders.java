package testData;

import lombok.SneakyThrows;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testData.containers.LoginTestDataContainer;

import java.lang.reflect.Method;
import java.util.List;

import static utils.FileHelper.readFromCsv;

public class StaticProviders {
    /**
     * @param m - argument from which file name can be obtained"
     * @return - two-dimensional array of data objects
     */
    @SuppressWarnings("unchecked")
    @SneakyThrows
    @DataProvider(name = "incorrectLoginData", parallel = true)
    public static Object[][] loginWithIncorrectData(Method m) {

//   Set the data from .csv file to List
        List<LoginTestDataContainer> loginTestData = readFromCsv(m.getAnnotation(Test.class).groups()[0],
                LoginTestDataContainer.class);

        LoginTestDataContainer[][] loginTestDataContainers = new LoginTestDataContainer[loginTestData.size()][];

//   FIll the parsed from .csv login test data to array
        for (int i = 0; i < loginTestData.size(); i++) {
            loginTestDataContainers[i] = new LoginTestDataContainer[]{loginTestData.get(i)};
        }
        return loginTestDataContainers;
    }
}