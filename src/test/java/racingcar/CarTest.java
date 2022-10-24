package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constants.CarStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class CarTest {
    private Car car;

    @ParameterizedTest
    @ValueSource(ints = {
            4, 5, 6, 7, 8, 9
    })
    void 자동차_전진(int randomNumber) {
        this.car = new Car(randomNumber);
        CarStatus actual = this.car.getCarStatus();
        CarStatus expected = CarStatus.FORWARD;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, 1, 2, 3
    })
    void 자동차_멈춤(int randomNumber) {
        this.car = new Car(randomNumber);
        CarStatus actual = this.car.getCarStatus();
        CarStatus expected = CarStatus.STOP;
        assertThat(actual).isEqualTo(expected);
    }
}
