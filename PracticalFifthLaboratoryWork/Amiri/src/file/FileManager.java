package file;

import java.io.*;

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

    public boolean write(String xml) {
        boolean success = false;
        try {
            if (path == null) {
                System.err.println("Couldn't find path, trying to create new file in this directory");
                File file = new File("file.json");
                if (file.createNewFile()) System.out.println("New file has been created successfully");
            }
            File file = new File(path);
            if (!file.exists()) {
                System.err.println("File doesn't exist, trying to create new file [" + path + "]");
                if (file.createNewFile()) System.out.println("New file has been created successfully");
            }
            if (xml.isEmpty()) throw new RuntimeException("Nothing found to be written in the file");
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream writer = new BufferedOutputStream(outputStream);
            writer.write(xml.getBytes());
            writer.flush();
            writer.close();
            outputStream.close();
            success = true;
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return success;
    }
}
