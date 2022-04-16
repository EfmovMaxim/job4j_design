package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new LinkedList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            list = reader.lines().filter(l -> {
                String[] splitLine = l.split(" ");
                return splitLine[splitLine.length - 2].equals("404");
            }).map(s -> s + System.lineSeparator())
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return list;
    }
    public static void save(List<String> log, String file) {
        try (PrintWriter pw = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            log.forEach(pw::print);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
        save(log, "404.txt");
    }
}