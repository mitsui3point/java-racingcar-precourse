package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.CarStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CarTest {
    private Car car;

    @ParameterizedTest
    @ValueSource(ints = {
            4, 5, 6, 7, 8, 9
    })
    void 자동차_전진(int randomNumber) {
        this.car = new Car(randomNumber);
        CarStatus actual = this.car.getStatus();
        CarStatus expected = CarStatus.FORWARD;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, 1, 2, 3
    })
    void 자동차_멈춤(int randomNumber) {
        this.car = new Car(randomNumber);
        CarStatus actual = this.car.getStatus();
        CarStatus expected = CarStatus.STOP;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -1, -2, -10, -9,
            10, 11, 32
    })
    void 자동차_랜덤번호_유효성체크(int randomNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> {
                            new Car(randomNumber);
                        }
                );
    }
}
