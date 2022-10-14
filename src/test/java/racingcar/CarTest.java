package racingcar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class CarTest {

    private Car car;

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 10
    })
    void 누적_회차_개수_유효성_체크(int roundsNumber) {
        assertThatNoException().isThrownBy(() -> {
            this.car = new Car(roundsNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -12, -10, -1, 0
    })
    void 누적_회차_개수_유효성_체크_예외(int roundsNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.car = new Car(roundsNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 10
    })
    void 자동차_누적_회차_세팅(int roundsNumber) {
        this.car = new Car(roundsNumber);
        int actual = this.car.getRounds().size();
        int expected = roundsNumber;
        assertThat(actual).isEqualTo(expected);
    }
}
