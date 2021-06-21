package tests.apiTests;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.find;

@Slf4j
public class testfortest {

    @Test
    @SneakyThrows
    public void test() {
        open("https://catalog.onliner.by/mobile/samsung/sma315fzkuser/prices");
        $("wefwefwef").click();
    }
}
