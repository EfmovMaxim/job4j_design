package ru.job4j.io;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String line;
        String[] splitLine;
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            while ((line = read.readLine()) != null) {

                if (line.isBlank() || line.startsWith("#")) {
                    continue;
                }

                splitLine = line.split("=");

                if (splitLine.length != 2
                        || splitLine[0].isBlank()
                        || splitLine[1].isBlank()) {
                    throw new IllegalArgumentException();
                }

                values.put(splitLine[0], splitLine[1]);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    public int getNumberOfValues() {
        return values.size();
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}