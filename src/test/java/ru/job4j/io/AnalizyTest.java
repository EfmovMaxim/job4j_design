package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void when2Periods() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");

        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("200 10:59:01");
            writer.println("500 11:01:02");
            writer.println("200 11:02:02 ");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.getPath(), target.getPath());

        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine(), is("10:57:01;10:59:01"));
            assertThat(reader.readLine(), is("11:01:02;11:02:02"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void when1Period() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");

        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.println("200 10:56:01");
            writer.println("500 10:57:01");
            writer.println("400 10:58:01");
            writer.println("500 10:59:01");
            writer.println("400 11:01:02");
            writer.println("200 11:02:02");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.getPath(), target.getPath());

        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine(), is("10:57:01;11:02:02"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void when0Period() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");

        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.println("200 10:56:01");
            writer.println("300 10:57:01");
            writer.println("300 10:58:01");
            writer.println("200 10:59:01");
            writer.println("300 11:01:02");
            writer.println("200 11:02:02");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.getPath(), target.getPath());

        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertTrue(reader.readLine().isBlank());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void whenLogEndsOn500() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");

        try (PrintWriter writer = new PrintWriter(new BufferedOutputStream(new FileOutputStream(source)))) {
            writer.println("200 10:56:01");
            writer.println("300 10:57:01");
            writer.println("300 10:58:01");
            writer.println("400 10:59:01");
            writer.println("400 11:01:02");
            writer.println("500 11:02:02");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Analizy analizy = new Analizy();
        analizy.unavailable(source.getPath(), target.getPath());

        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            assertThat(reader.readLine(), is("10:59:01;Server not start"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}