package Domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static Settings instance = null;
    private String fileName;
    private String location;

    private Settings() {
        loadSettings();
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    private void loadSettings() {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("C:\\Users\\40766\\OneDrive\\Desktop\\IOT_test4\\settings.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileName = properties.getProperty("nume_fisier");
        location = properties.getProperty("locatie_fisier");
    }

    public String getFileName() {
        return fileName;
    }

    public String getLocation() {
        return location;
    }
}