package utils;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FileHelper {

    @SneakyThrows
    public static File getFileToUpload(String fileName) {
        return getFileFromResources("filesToUpload", fileName);
    }

    @SneakyThrows
    public static File getFileFromResources(String... filePathAndName){
        List<String> paths = new ArrayList<>(Arrays.asList("test", "resources"));
        paths.addAll(Arrays.asList(filePathAndName));
        try {
            Path resourceDirectory = Paths.get("src", paths.toArray(String[]::new));
            return resourceDirectory.toFile();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     *
     * @param filename - name of .csv file with data
     * @param classToDeserializeTo - model class contains fields for csv. file
     * @return - list of objects data to deserialize to from .csv file
     */
    @SneakyThrows
    public static <T> List<T> readFromCsv(String filename, Class<T> classToDeserializeTo) {
        try{
            return new CsvToBeanBuilder<T>(Files
                    .newBufferedReader(Paths.get(ClassLoader.getSystemResource(filename).toURI())))
                    .withType(classToDeserializeTo)
                    .build()
                    .parse();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}