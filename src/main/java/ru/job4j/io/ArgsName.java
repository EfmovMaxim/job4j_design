package ru.job4j.io;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void checkArgs(String[] args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Check arguments");
        }
    }

    public void parse(String[] args) {
        checkArgs(args);

        String[] param = null;
        for (String arg : args) {
            param = arg.substring(1).split("=");
            if (param.length != 2 || param[0].isBlank() || param[1].isBlank()) {
                throw new IllegalArgumentException("Check arguments");
            }

            values.put(param[0], param[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}