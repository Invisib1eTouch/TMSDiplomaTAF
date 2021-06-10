package testData.containers;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginTestDataContainer {

    @CsvBindByName(column = "login")
    private String login;

    @CsvBindByName(column = "password")
    private String password;

    @CsvBindByName(column = "login field error")
    private String loginFieldError;

    @CsvBindByName(column = "password field error")
    private String passwordFieldError;
}
