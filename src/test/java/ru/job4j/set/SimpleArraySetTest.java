package ru.job4j.set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleArraySetTest {

    @Test
    void whenAddNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.add(2)).isTrue();
        assertThat(set.contains(2)).isTrue();
        assertThat(set.add(2)).isFalse();
        assertThat(set.contains(3)).isFalse();
        assertThat(set.add(3)).isTrue();
        assertThat(set.contains(3)).isTrue();
        assertThat(set.add(3)).isFalse();
    }

    @Test
    void whenAddNull() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenContainsEmptySet() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(1)).isFalse();
    }

    @Test
    void whenContainsWithNullValue() {
        SimpleSet<Integer> set = new SimpleArraySet<>();
        assertThat(set.contains(null)).isFalse();
    }

    @Test
    void whenAddSomeElementsNonNullAndNull() {
        SimpleSet<String> set = new SimpleArraySet<>();
        assertThat(set.add("one")).isTrue();
        assertThat(set.contains("one")).isTrue();
        assertThat(set.contains("two")).isFalse();
        assertThat(set.add("two")).isTrue();
        assertThat(set.contains("two")).isTrue();
        assertThat(set.add("three")).isTrue();
        assertThat(set.contains("three")).isTrue();
        assertThat(set.add("one")).isFalse();
        assertThat(set.contains("four")).isFalse();
        assertThat(set.add("four")).isTrue();
        assertThat(set.contains("four")).isTrue();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.contains("five")).isFalse();
        assertThat(set.add("five")).isTrue();
        assertThat(set.contains("five")).isTrue();
        assertThat(set.add(null)).isFalse();
    }
}