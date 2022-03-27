package ru.job4j.io;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.StringJoiner;

public class Analizy {
    public void unavailable(String source, String target) {
        String line;
        StringJoiner rsl = new StringJoiner(System.lineSeparator());

        try (BufferedReader in = new BufferedReader(new FileReader(source))) {

            boolean openPeriod = false;
            String[] subLine = null;
            while ((line = in.readLine()) != null)  {

                if (!openPeriod && (line.contains("400") || line.contains("500"))) {
                    openPeriod = true;
                    subLine = line.split(" ");
                } else if (openPeriod && !(line.contains("400") || line.contains("500"))) {
                    openPeriod = false;
                    rsl.add(subLine[1] + ";" + line.split(" ")[1]);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/LogAnalizy1.txt", "./data/target1.txt");
        analizy.unavailable("./data/LogAnalizy2.txt", "./data/target2.txt");
    }
}