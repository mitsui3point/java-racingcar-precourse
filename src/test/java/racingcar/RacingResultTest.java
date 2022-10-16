package racingcar;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RacingResultTest {
    private RacingResult racingResult;

    @ParameterizedTest
    @NullSource
    void 전체기록_생성자_null_체크(Rounds rounds) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> {
                            this.racingResult = new RacingResult(rounds);
                        }
                );
    }

    @ParameterizedTest
    @CsvSource(value = {
            "kim,park,yoo:9"
    }, delimiterString = ":")
    void 최종_전진횟수_최대기록(String names, int roundNumber) {
        Rounds rounds = new Rounds(names, roundNumber);
        this.racingResult = new RacingResult(rounds);
        int actual = this.racingResult.getMaxForwardCount();
        List<Integer> expected = new ArrayList<>();
        for (String name: names.split(Rounds.COMMA)) {
             expected.add(
                     rounds.getRounds()
                             .get(name)
                             .get(roundNumber - 1)
                             .length()
             );
        }
        assertThat(actual).isIn(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "kim,park,yoo:9"
    }, delimiterString = ":")
    void 우승자_도출(String names, int roundNumber) {
        Rounds rounds = new Rounds(names, roundNumber);
        this.racingResult = new RacingResult(rounds);
        for(String winner: this.racingResult.getRoundWinners()) {
            List<String> carStatuses = rounds.getRounds()
                    .get(winner);
            int actual = carStatuses.get(carStatuses.size() - 1)
                    .length();
            int expected = this.racingResult.getMaxForwardCount();
            assertThat(actual).isEqualTo(expected);
        }
    }
}
