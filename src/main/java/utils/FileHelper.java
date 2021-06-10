package utils;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

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
}
