package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class CarTest {

    private Car car;

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 10
    })
    void 자동차_상태_개수_유효성_체크(int carStatusCount) {
        assertThatNoException().isThrownBy(() -> {
            this.car = new Car(carStatusCount);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -12, -10, -1, 0
    })
    void 자동차_상태_개수_유효성_체크_예외(int carStatusCount) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.car = new Car(carStatusCount);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 10
    })
    void 자동차_객체에_자동차_상태_누적(int carStatusCount) {
        this.car = new Car(carStatusCount);
        int actual = this.car.getCarStatuses().size();
        int expected = carStatusCount;
        assertThat(actual).isEqualTo(expected);
    }
}
