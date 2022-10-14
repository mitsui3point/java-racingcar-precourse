package racingcar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class RoundsTest {

    private Rounds rounds;

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 10
    })
    void 회차_누적_개수_유효성_체크(int roundsNumber) {
        assertThatNoException().isThrownBy(() -> {
            this.rounds = new Rounds(roundsNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            -12, -10, -1, 0
    })
    void 회차_누적_개수_유효성_체크_예외(int roundsNumber) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.rounds = new Rounds(roundsNumber);
        });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 9, 15
    })
    void 회차_누적(int roundsNumber) {
        this.rounds = new Rounds(roundsNumber);
        int actual = this.rounds.getRounds().size();
        int expected = roundsNumber;
        assertThat(actual).isEqualTo(expected);
    }
}
