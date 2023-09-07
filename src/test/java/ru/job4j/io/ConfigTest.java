package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithCommentAndWhitespace() {
        String path = "./data/pair_with_comment_and_whitespace.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Dmitry Arinin");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenPairWithoutEqually() {
        String path = "./data/pair_without_equally.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenValueWithDuplicateEqually() {
        String path = "./data/value_with_duplicate_equally.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Dmitry Arinin=DIgitex");
    }
}