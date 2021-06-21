package testData.containers;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class is used for assign fields from csv file
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTestDataContainer {

    @CsvBindByName(column = "auth")
    private String login;

    @CsvBindByName(column = "password")
    private String password;

    @CsvBindByName(column = "auth field error")
    private String loginFieldError;

    @CsvBindByName(column = "password field error")
    private String passwordFieldError;
}