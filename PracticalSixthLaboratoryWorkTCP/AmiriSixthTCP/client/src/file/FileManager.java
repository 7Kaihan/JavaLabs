package file;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileManager {
    private String path;

    public FileManager(String path) {
        this.path = path;
    }

    public String read() {
        StringBuilder fileContents = new StringBuilder();
        try {
            if (path == null) throw new RuntimeException("path is empty");
            File file = new File(path);
            if (!file.exists()) throw new RuntimeException("file doesn't exist");
            if (!file.canRead()) throw new RuntimeException("can't read the file");
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream);
            int i;
            while ((i = reader.read()) != -1)
                fileContents.append((char) i);
            reader.close();
            fileInputStream.close();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
        return fileContents.toString();
    }
}
