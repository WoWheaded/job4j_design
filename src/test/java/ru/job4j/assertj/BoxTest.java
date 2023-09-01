package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(7, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object")
                .endsWith("object")
                .doesNotContain("Sphere");
    }

    @Test
    void get8Vertices() {
        Box box = new Box(8, 5);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(8)
                .isGreaterThan(3)
                .isPositive();
    }

    @Test
    void getZeroVertices() {
        Box box = new Box(0, 7);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isEqualTo(0)
                .isLessThan(1)
                .isZero();
    }

    @Test
    void when5VerticesThenFalse() {
        Box box = new Box(5, 6);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse()
                .isNotEqualTo(true);
    }

    @Test
    void when4VerticesThenTrue() {
        Box box = new Box(4, 6);
        boolean rsl = box.isExist();
        assertThat(rsl).isNotEqualTo(false)
                .isTrue();
    }

    @Test
    void when3EdgesThenArea314Dot16() {
        Box box = new Box(0, 5);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(314.16d, withPrecision(0.004d))
                .isGreaterThan(314.12d)
                .isBetween(310.00d, 320.00d);
    }

    @Test
    void when4EdgesThenArea27Dot71() {
        Box box = new Box(4, 4);
        double rsl = box.getArea();
        assertThat(rsl).isCloseTo(27.71d, Percentage.withPercentage(1.0d))
                .isLessThan(27.80d)
                .isNotNegative();
    }
}