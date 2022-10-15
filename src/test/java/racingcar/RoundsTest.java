package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RoundsTest {

    private Rounds rounds;

    @ParameterizedTest
    @CsvSource(value = {
            "kim,park,leele:2",
            "hwa11,cho,eom,:5",
            "hwang,choo,eom,kang:8",
    }, delimiterString = ":")
    void 회차_자동차_상태_세팅(String names, int roundNumber) {
        this.rounds = new Rounds(names, roundNumber);
        for (int nameIndex = 0; nameIndex < names.split(",").length; nameIndex++) {
            int actual = this.rounds.getRounds()
                    .get(names.split(",")[nameIndex])
                    .size();
            int expected = roundNumber;
            assertThat(actual).isEqualTo(expected);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "kim,park,leele:3",
            "hwa11,cho,eom,:3",
            "hwang,choo,eom,kang:4",
    }, delimiterString = ":")
    void 회차_목록_이름_세팅(String names, int expected) {
        this.rounds = new Rounds(names, 1);
        int actual = this.rounds.getRounds().size();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "kim,park ㅇ,leele",
            "hwang,cho  o,eom,kang",
            "hwa11g,cho,eom,kang"})
    void 회차_목록_이름_세팅_예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.rounds = new Rounds(names, 1);
        });
    }
}
