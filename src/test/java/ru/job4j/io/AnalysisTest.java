package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AnalysisTest {
    @Test
    void whenServerHave2Errors(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("500 10:57:01");
            out.println("400 10:58:01");
            out.println("300 10:59:01");
            out.println("500 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expected = List.of("10:57:01;10:59:01;", "11:01:02;11:02:02;");
        List<String> actual;
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            actual = reader.lines().toList();
        }
        assertThat(expected).isEqualTo(actual);
    }

    @Test
    void whenServerHaveNoErrors(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01");
            out.println("300 10:57:01");
            out.println("200 10:58:01");
            out.println("300 10:59:01");
            out.println("300 11:01:02");
            out.println("200 11:02:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> expected = Collections.emptyList();
        List<String> actual;
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            actual = reader.lines().toList();
        }
        assertThat(expected).isEqualTo(actual);
    }
}