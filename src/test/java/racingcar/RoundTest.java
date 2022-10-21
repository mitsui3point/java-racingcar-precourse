package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.CarStatus;
import racingcar.constant.ErrorMessage;
import racingcar.constant.Regex;
import racingcar.util.NumberGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RoundTest {
    private Round round;
    private int maxRegistCarCount;
    private String names;
    private Map<String, CarStatus> expected;
    private NumberGenerator numberGenerator;
    @BeforeEach
    void setUp() {
        this.names = "pobi,crong,honux";
        String[] splitNames = this.names.split(Regex.COMMA);

        this.numberGenerator = () -> new int[]{5, 3, 4};
        this.maxRegistCarCount = splitNames.length;
        this.expected =  new LinkedHashMap<>();
        for (int registCarIndex = 0; registCarIndex < maxRegistCarCount; registCarIndex++) {
            this.expected.put(
                    splitNames[registCarIndex],
                    new Car(this.numberGenerator.generateNumber()[registCarIndex]).getStatus()
            );
        }
    }

    @Test
    void 회차_저장() {
        this.round = new Round(this.names, this.numberGenerator);
        Map<String, CarStatus> actual = this.round.getRound();
        System.out.println(actual);
        System.out.println(this.expected);
        assertThat(actual).isEqualTo(this.expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            ",,",

            " ,,",
            ", ,",
            ",, "
    })
    void 회차_저장_이름목록_예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> this.round = new Round(names, this.numberGenerator)
                )
                .withMessage(ErrorMessage.CAR_NAMES_IS_EMPTY);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            " ,crong,honux",
            "pobi, ,honux",
            "pobi,crong, ",

            "\t,crong,honux",
            "pobi,\t,honux",
            "pobi,crong,\t",

            "qwerty,crong,honux",
            "pobi,qwerty,honux",
            "pobi,crong,qwerty",

            "qw rty,crong,honux",
            "pobi,qw rty,honux",
            "pobi,crong,qw rty"
    })
    void 회차_저장_이름_예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> this.round = new Round(names, this.numberGenerator)
                )
                .withMessage(ErrorMessage.CAR_NAME_LENGTH_IS_OUT_OF_RANGE);
    }

    @Test
    void 회차_저장_랜덤번호목록_빈값_예외() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> this.round = new Round(this.names, null)
                )
                .withMessage(ErrorMessage.NUMBER_GENERATOR_IS_NULL);
    }

    @Test
    void 회차_저장_랜덤번호목록_범위초과_예외() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> this.round = new Round(this.names, () -> new int[]{1, 2, 3, 4})
                )
                .withMessage(ErrorMessage.RANDOM_NUMBERS_LENGTH_IS_INVALID);
    }

    @Test
    void 회차_저장_랜덤번호목록_범위미달_예외() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> this.round = new Round(this.names, () -> new int[]{1, 2})
                )
                .withMessage(ErrorMessage.RANDOM_NUMBERS_LENGTH_IS_INVALID);
    }
}