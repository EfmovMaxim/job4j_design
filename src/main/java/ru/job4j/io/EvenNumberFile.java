package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        int read;
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }

            String[] lines = text.toString().split(System.lineSeparator());

            for (String line : lines) {
                System.out.println((Integer.parseInt(line) % 2 == 0) ? "четное" : "нечетное");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
