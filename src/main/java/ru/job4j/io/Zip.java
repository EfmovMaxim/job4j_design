package ru.job4j.io;
import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class Zip {

    private static void checkArgs(ArgsName args) {
        File input = new File(args.get("d"));
        if (!input.exists() || !input.isDirectory())  {
            throw new IllegalArgumentException("Set correct input directory");
        }
    }

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new
                FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toAbsolutePath().toString()));

                try (BufferedInputStream out = new BufferedInputStream(new
                        FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new
                FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new
                    FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        checkArgs(argsName);

        Path input = Path.of(argsName.get("d"));
        String exclude = argsName.get("e");
        String output = argsName.get("o");


        Zip.packFiles(Search.search(input, f -> f.toFile().isDirectory() || !f.toString().endsWith(exclude)), new File(output));

    }

}