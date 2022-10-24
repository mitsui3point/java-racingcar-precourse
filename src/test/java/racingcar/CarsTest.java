package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constants.CarStatus;
import racingcar.constants.ErrorMessage;
import racingcar.util.NumberGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CarsTest {
    private Cars cars;
    private String names;
    private int finalRoundIndex;
    private List<CarStatus> expected;

    private class NumberGeneratorTest implements NumberGenerator {
        @Override
        public int generateNumber() {
            return CarStatus.MIN_FORWARD_NUMBER;
        }
    }

    @BeforeEach
    void setUp() {
        this.names = "pobi,crong,honux";
        this.finalRoundIndex = 4;
        this.expected = new ArrayList<>();
        for (int roundIndex = 0; roundIndex < this.finalRoundIndex; roundIndex++) {
            this.expected.add(new Car(new NumberGeneratorTest().generateNumber()).getCarStatus());
        }
    }

    @Test
    void 자동차_목록_추가() {
        this.cars = new Cars(this.names, this.finalRoundIndex, new NumberGeneratorTest());
        List<CarStatus> actual = new ArrayList<>();
        for (String name : names.split(",")) {
            actual = this.cars.getCars().get(name);
        }
        System.out.println("actual:" + actual);
        System.out.println("this.expected:" + this.expected);
        assertThat(actual).isEqualTo(this.expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 자동차_목록_추가_이름목록예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    this.cars = new Cars(names, this.finalRoundIndex, new NumberGeneratorTest());
                }
        ).withMessageContaining(ErrorMessage.NAMES_IS_NULL.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "pobi,crong1,honux",
            "pobi,crong,honux1",
            "pobi,crong,honux1",
            "pobi,cron g,honux",
    })
    void 자동차_목록_추가_이름예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    this.cars = new Cars(names, this.finalRoundIndex, new NumberGeneratorTest());
                }
        ).withMessageContaining(ErrorMessage.NAME_IS_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, -1, -100
    })
    void 자동차_목록_추가_회차예외(int finalRoundIndex) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    this.cars = new Cars(this.names, finalRoundIndex, new NumberGeneratorTest());
                }
        ).withMessageContaining(ErrorMessage.FINAL_ROUND_INDEX_IS_OUT_OF_RANGE.getMessage());
    }

    @Test
    void 자동차_목록_추가_랜덤객체예외() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    this.cars = new Cars(this.names, this.finalRoundIndex, null);
                }
        ).withMessageContaining(ErrorMessage.NUMBER_GENERATOR_IS_NULL.getMessage());
    }
}