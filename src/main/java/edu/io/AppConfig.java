package edu.io;

import org.yaml.snakeyaml.Yaml;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    private static AppConfig instance;
    private Map<String, Object> config = new HashMap<>();

    private AppConfig() {

    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public void set(String name, Object value) {
        config.put(name, value);
    }

    public Object get(String name) {
        return config.get(name);
    }

    public <T> T get(String name, Class<T> type) {
        Object value = config.get(name);
        return type.cast(value);
    }

    public void load(String filename) {
        try {
            FileReader fileReader = new FileReader(filename);
            Yaml yaml = new Yaml();
            Map<String, Object> loaded = yaml.load(fileReader);
            fileReader.close();

            if (loaded != null) {
                config = loaded;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(String filename) {
        try {
            FileWriter fileWriter = new FileWriter(filename);
            Yaml yaml = new Yaml();
            yaml.dump(config, fileWriter);
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
