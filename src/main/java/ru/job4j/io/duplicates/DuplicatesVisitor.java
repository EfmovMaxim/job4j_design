package ru.job4j.io.duplicates;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private LinkedList<Path> duplicates = new LinkedList<>();
    private Set<FileProperty> tempSet = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (!tempSet.add(new FileProperty(attrs.size(), file.getFileName().toString()))) {
            duplicates.add(file);
        }

        return super.visitFile(file, attrs);
    }

    public LinkedList<Path> getDuplicates() {
        return duplicates;
    }
}