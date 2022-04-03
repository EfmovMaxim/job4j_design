package ru.job4j.io;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        Path start = Paths.get("/home/mefimov/Музыка/");
        search(start, p ->
                p.toFile().getName().endsWith("log")).forEach(System.out::println);
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