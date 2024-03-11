package file;

import colorizedConsole.ConsoleColor;

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
            System.out.println(ConsoleColor.RED_BACKGROUND+exception.getMessage()+ConsoleColor.RESET);
        }
        return fileContents.toString();
    }

    public boolean write(String xml) {
        boolean success = false;
        try {
            if (path == null) {
                System.out.println(ConsoleColor.RED_BACKGROUND+"Couldn't find path, trying to create new file in this directory"+ConsoleColor.RESET);
                File file = new File("file.xml");
                if (file.createNewFile()) System.out.println(ConsoleColor.GREEN_BACKGROUND+"New file has been created successfully"+ConsoleColor.RESET);
            }
            File file = new File(path);
            if (!file.exists()) {
                System.out.println(ConsoleColor.RED_BACKGROUND+"File doesn't exist, trying to create new file ["+ConsoleColor.RESET+"" + ConsoleColor.BLUE_UNDERLINED+path +ConsoleColor.RESET+""+ConsoleColor.RED_BACKGROUND +"]"+ConsoleColor.RESET);
                if (file.createNewFile()) System.out.println(ConsoleColor.GREEN_BACKGROUND+"New file has been created successfully"+ConsoleColor.RESET);
            }
            if (xml.isEmpty()) throw new RuntimeException(ConsoleColor.RED_BACKGROUND+"Nothing found to be written in the file"+ConsoleColor.RESET);
            FileOutputStream outputStream = new FileOutputStream(file);
            BufferedOutputStream writer = new BufferedOutputStream(outputStream);
            writer.write(xml.getBytes());
            writer.flush();
            writer.close();
            outputStream.close();
            success = true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return success;
    }
}
