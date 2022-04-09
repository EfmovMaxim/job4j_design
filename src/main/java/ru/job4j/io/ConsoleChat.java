package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleChat {
    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private PrintWriter logPrintWriter = null;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;

        try {
            this.logPrintWriter = new PrintWriter(new FileWriter(path, Charset.forName("UTF-8")));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void run() {

        List<String> phrases = readPhrases();

        if (phrases.isEmpty()) {
            return;
        }

        boolean run = true;
        Scanner sc = new Scanner(System.in);
        String question;
        boolean work = true;
        Random random = new Random();
        LinkedList<String> log = new LinkedList<>();
        String answer;

        while (run) {
            System.out.print("Введит вопрос или команду: ");
            question = sc.next();
            log.add(question);
            switch (question) {
                case OUT:
                    run = false;
                    break;
                case CONTINUE:
                    work = true;
                    break;
                case STOP:
                    work = false;
                    break;
                default: if (work) {
                    answer = phrases.get(random.nextInt(phrases.size()));
                    System.out.println(answer);
                    log.add(answer);
                }
            }
        }

        saveLog(log);
        sc.close();
    }

    private List<String> readPhrases() {

        List<String> phrases = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(botAnswers, Charset.forName("UTF-8")))) {
            return bufferedReader.lines().collect(Collectors.toCollection(ArrayList::new));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private void saveLog(List<String> log) {
        log.forEach(logPrintWriter::println);
        logPrintWriter.close();
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatlog.txt", "./data/answers.txt");
        cc.run();
    }
}