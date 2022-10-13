package racingcar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.RoundStatus;

import static org.assertj.core.api.Assertions.*;

public class RoundTest {
    private Round round;

    @ParameterizedTest
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void 회차_전진_판단_및_결과_저장(int randomNumber) {
        this.round = new Round(randomNumber);
        RoundStatus actual = this.round.getRoundStatus();
        RoundStatus expected = RoundStatus.FORWARD;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    void 회차_멈춤_판단_및_결과_저장(int randomNumber) {
        this.round = new Round(randomNumber);
        RoundStatus actual = this.round.getRoundStatus();
        RoundStatus expected = RoundStatus.STOP;
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void 할당된_회차_랜덤번호_유효성_체크(int randomNumber) {
        assertThatNoException().isThrownBy(() -> {
            this.round = new Round(randomNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, 10, Integer.MAX_VALUE})
    void 할당된_회차_랜덤번호_유효성_체크_예외처리(int randomNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.round = new Round(randomNumber);
        });
    }
}
