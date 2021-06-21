package utils;

import lombok.SneakyThrows;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class AllureUtilities {

    @SneakyThrows
    public static void removeParametersInReport() {
        File dir = new File(String.valueOf(Paths.get("target", "allure-results")));
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                if (child.getName().contains("result")) {
                    removeParameterInJson(child);
                }
            }
        } else {
            throw new Exception(String.format("Directory %s/allure-results does not exist", System.getProperty("user.dir")));
        }
    }

    @SneakyThrows
    private static void removeParameterInJson(File fileToBeUpdated) {
        try {
            FileReader reader = new FileReader(fileToBeUpdated);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            List<Object> newParametersValues = new LinkedList<>();
            jsonObject.put("parameters", newParametersValues);

            try (FileWriter file = new FileWriter(fileToBeUpdated)) {
                file.write(jsonObject.toString());
            }

        } catch (NullPointerException | IOException e) {
            throw new Exception(String.format("Something went wrong when updating %s", fileToBeUpdated.getName()), e);
        }
    }
}
