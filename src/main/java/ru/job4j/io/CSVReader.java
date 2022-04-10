package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

public class CSVReader {
    public void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String filter = argsName.get("filter");
        List<Integer> columns = new ArrayList<>();

        boolean firstRow = true;
        boolean firstColumn;
        String value;
        StringBuilder rsl = new StringBuilder();
        int maxColumn;

        try (Scanner scFile = new Scanner(new FileReader(path)).useDelimiter(System.lineSeparator())) {

            while (scFile.hasNext()) {
                String[] scLine = scFile.next().split(delimiter);

                firstColumn = true;
                for (int columnNum = 0; columnNum < scLine.length; columnNum++) {
                    value = scLine[columnNum];

                    if ((firstRow && filter.contains(value))
                    || (!firstRow && columns.contains(columnNum))) {
                        if (firstRow) {
                            columns.add(columnNum);
                        }
                        if (!firstColumn) {
                            rsl.append(delimiter);
                        } else {
                            firstColumn = false;
                        }
                        rsl.append(value);
                    }
                }

                if (firstRow) {
                    firstRow = false;
                }

                if (scFile.hasNext()) {
                    rsl.append(System.lineSeparator());
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        if (out.equals("stdout")) {
            System.out.println(rsl);
        } else {
            writeFile(rsl, out);
        }
    }

    private void writeFile(StringBuilder text, String path) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8")))) {
            pw.println(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}