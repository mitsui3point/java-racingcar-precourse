package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CarsTest {

    private Cars cars;

    @ParameterizedTest
    @CsvSource(value = {
            "kim,park,leele:3",
            "hwa11,cho,eom,:3",
            "hwang,choo,eom,kang:4",
    }, delimiterString = ":")
    void 자동차_목록_세팅_이름(String names, int expected) {
        this.cars = new Cars(names, 1);
        int actual = this.cars.getCars().size();
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "kim,park ㅇ,leele",
            "hwang,cho  o,eom,kang",
            "hwa11g,cho,eom,kang"})
    void 자동차_목록_세팅_이름_예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.cars = new Cars(names, 1);
        });
    }
}
