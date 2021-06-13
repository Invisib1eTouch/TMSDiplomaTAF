package utils;

import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHelper {

    @SneakyThrows
    public static File getFileToUpload(String fileName) {
        Path resourceDirectory = Paths.get("src", "test", "resources", "filesToUpload", fileName);
        return resourceDirectory.toFile();
    }

    public static File downloadFile(URL url, String fileExt) throws Exception {
        Path resourceDirectory = Paths.get("src", "test", "resources", "downloads", "tmpFile" + fileExt);
        File downloadedFile = resourceDirectory.toFile();
        FileUtils.copyURLToFile(url, downloadedFile);
        return downloadedFile;
    }

    @SneakyThrows
    public static boolean compareFiles(File file, File otherFile){
        return FileUtils.contentEquals(file, otherFile);
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
