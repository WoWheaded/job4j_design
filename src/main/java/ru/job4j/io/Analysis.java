package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter writer = new PrintWriter(new FileWriter(target))) {
            String line;
            String startTime = null;
            boolean serverDown = false;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String status = parts[0];
                String time = parts[1];
                if ((status.equals("400") || status.equals("500")) && !serverDown) {
                    startTime = time;
                    serverDown = true;
                } else if ((status.equals("200") || status.equals("300")) && serverDown) {
                    writer.println(startTime + ";" + time + ";");
                    serverDown = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
