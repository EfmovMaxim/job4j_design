package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        //Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        Path path = Path.of("/home/mefimov/Документы/учебник/");
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(path, dv);

        System.out.println(dv.getDuplicates());


    }
}