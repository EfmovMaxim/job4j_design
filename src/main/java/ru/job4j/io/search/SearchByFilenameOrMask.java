package ru.job4j.io.search;

import ru.job4j.io.Search;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

public class SearchByFilenameOrMask {


    private void checkArgs(ArgsName argsName) {

        String dir = argsName.get("d");
        String typeSearch = argsName.get("t");

        Path dirFile = Paths.get(dir);
        if (Files.notExists(dirFile) || !Files.isDirectory(dirFile)) {
            throw new IllegalArgumentException("Choose correct dir");
        }

        if (!typeSearch.equals("mask") && !typeSearch.equals("regex")
        && !typeSearch.equals("name")) {
            throw new IllegalArgumentException("Choose correct search type");
        }


    }

    public void search(ArgsName argsName) {

        checkArgs(argsName);

        Path dir = Paths.get(argsName.get("d"));
        String searchString = argsName.get("n");
        String typeSearch = argsName.get("t");
        String logPath = argsName.get("o");

        List<Path> rsl = new LinkedList<>();

        switch (typeSearch) {
            case "name":
                rsl = Search.search(dir, f -> f.toFile().getName().equals(searchString));
                break;
            case "mask":
                rsl = Search.search(dir, f -> f.toFile().getName().endsWith(searchString.substring(1)));
                break;
            case
                    "regex": rsl = Search.search(dir, f -> f.toFile().getName().matches(searchString));
                    break;
            default:
        }

        rsl.forEach(System.out::println);
        writeLog(rsl, logPath);

    }

    public void writeLog(List<Path> paths, String logPath) {

        try (PrintWriter pw = new PrintWriter(logPath)) {
            paths.forEach(pw::println);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);

        SearchByFilenameOrMask search = new SearchByFilenameOrMask();
        search.search(argsName);


    }
}
