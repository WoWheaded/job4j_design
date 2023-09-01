package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ModelTest {

    @Test
    void checkBoolean() {
        Model model = new Model(1, 5.255d, "namde", true);
        boolean result = model.isCondition();
        assertThat(result).isTrue();
    }
}