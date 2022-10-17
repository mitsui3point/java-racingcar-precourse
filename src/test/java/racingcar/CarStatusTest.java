package racingcar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.Status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class CarStatusTest {
    private CarStatus carStatus;

    @ParameterizedTest
    @ValueSource(ints = {
            4, 5, 6, 7, 8, 9
    })
    void 자동차상태_전진(int randomNumber) {
        this.carStatus = new CarStatus(randomNumber);
        Status actual = this.carStatus.get();
        Status expected = Status.FORWARD;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, 1, 2, 3
    })
    void 자동차상태_멈춤(int randomNumber) {
        this.carStatus = new CarStatus(randomNumber);
        Status actual = this.carStatus.get();
        Status expected = Status.STOP;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, -1, -2, -10, -9,
            10, 11, 32
    })
    void 자동차상태_랜덤번호_유효성체크(int randomNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> {
                            new CarStatus(randomNumber);
                        }
                );
    }
}
