package ru.job4j.io;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    private static void checkArgs(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("Check arguments");
        }

        File file = new File(args[0]);
        if (!file.exists() || !file.isDirectory()) {
            throw new IllegalArgumentException("set correct directory");
        }

    }

    public static void main(String[] args) throws IOException {
        checkArgs(args);

        Path start = Paths.get(args[0]);
        search(start, p ->
                p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) {
        SearchFiles searcher = new SearchFiles(condition);

        try {
            Files.walkFileTree(root, searcher);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return searcher.getPaths();
    }
}