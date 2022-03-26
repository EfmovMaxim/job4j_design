package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public static List<String> filter(String file) {
        List<String> list = new LinkedList<>();
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
// вариант 1:
//            while (reader.read() != -1) {
//                line = reader.readLine();
//                String[] splitLine = line.split(" ");
//                if (splitLine[splitLine.length - 2].equals("404")) {
//                    list.add(line);
//                }
//            }
// вариант 2:
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
    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}