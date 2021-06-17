package tests;

import lombok.SneakyThrows;
import org.testng.annotations.Test;
import services.DBExecutor;

public class BDTests {

    @SneakyThrows
    @Test
    public void qwerty(){
        var dbResultSet = DBExecutor.executeQuery("SELECT * FROM sanya_pidor");

        var test = dbResultSet.next();
        var dbResult = dbResultSet.getString("value_hueliu");
        System.out.println(dbResultSet);
    }
}
