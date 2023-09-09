package ru.job4j.chat;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    private final Random random = new Random();
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> logLines = new ArrayList<>();
        Scanner reader = new Scanner(System.in);
        String line = reader.nextLine();
        boolean pause = false;
        List<String> phrases = readPhrases();
        while (!OUT.equals(line)) {
            logLines.add("User : " + line);
            if (STOP.equals(line)) {
                pause = true;
            } else if (CONTINUE.equals(line)) {
                pause = false;
            }
            if (!pause) {
                String answer = getBotResponse(phrases);
                logLines.add("BOT : " + answer);
                System.out.println("BOT : " + answer);
            }
            line = reader.nextLine();
        }
        logLines.add(line);
        saveLog(logLines);
    }

    private List<String> readPhrases() {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            phrases = reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private String getBotResponse(List<String> phrases) {
        int index = random.nextInt(phrases.size());
        return phrases.get(index);
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8)))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/chatLog.txt", "data/botResponses.txt");
        consoleChat.run();
    }
}
