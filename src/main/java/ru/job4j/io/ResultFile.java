package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class ResultFile {

    public static void main(String[] args) {
        int i, j;
        String row = "";
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (i = 1; i < 10; i++) {
                for (j = 1; j < 10; j++) {
                    row += String.format(" %d x %d = %d ", i, j, i * j);
                }
                row += System.lineSeparator();
                out.write(row.getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
