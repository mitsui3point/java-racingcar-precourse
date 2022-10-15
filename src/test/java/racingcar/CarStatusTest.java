package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.CarAction;

import static org.assertj.core.api.Assertions.*;

public class CarStatusTest {
    private CarStatus carStatus;

    @ParameterizedTest
    @ValueSource(ints = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    })
    void 자동차_상태_할당된_랜덤번호_유효성_체크(int randomNumber) {
        assertThatNoException().isThrownBy(() -> {
            this.carStatus = new CarStatus(randomNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -1, -2 , -3,
            10, 11, 20, 500, 5242
    })
    void 자동차_상태_할당된_랜덤번호_유효성_체크_예외(int randomNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    this.carStatus = new CarStatus(randomNumber);
                });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, 1, 2, 3
    })
    void 자동차_상태_멈춤(int randomNumber) {
        this.carStatus = new CarStatus(randomNumber);
        CarAction actual = this.carStatus.getCarAction();
        CarAction expected = CarAction.STOP;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            4, 5, 6, 7, 8, 9
    })
    void 자동차_상태_전진(int randomNumber) {
        this.carStatus = new CarStatus(randomNumber);
        CarAction actual = this.carStatus.getCarAction();
        CarAction expected = CarAction.FORWARD;
        assertThat(actual).isEqualTo(expected);
    }
}
