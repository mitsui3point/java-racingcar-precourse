package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.constants.CarStatus;
import racingcar.util.NumberGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ResultTest {
    private Result result;
    private List<LinkedHashMap> expected;
    private Cars cars;

    @BeforeEach
    void setUp() {
        String names = "pobi,crong,honux";
        int finalRoundIndex = 5;
        NumberGenerator numberGenerator = new NumberGenerator() {
            @Override
            public int generateNumber() {
                return CarStatus.MIN_FORWARD_NUMBER ;
            }
        };
        this.cars = new Cars(names, finalRoundIndex, numberGenerator);
        this.expected = new ArrayList<>();
        LinkedHashMap<String, String> round = new LinkedHashMap<>();
        round.put("pobi", "");
        round.put("crong", "");
        round.put("honux", "");
        for (int roundIndex = 0; roundIndex < finalRoundIndex; roundIndex++) {
            round.put("pobi", round.get("pobi").concat("-"));
            round.put("crong", round.get("crong").concat("-"));
            round.put("honux", round.get("honux").concat("-"));
            this.expected.add(new LinkedHashMap(round));
        }
    }

    @Test
    void 누적_회차_결과_집계() {
        this.result = new Result(this.cars);
        List<LinkedHashMap<String, String>> actual = this.result.getRoundResults();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 누적_회차_결과_우승자_목록() {
        this.result = new Result(this.cars);
        List<String> actual = this.result.getWinners();
        List<String> expected = new ArrayList<>(Arrays.asList(new String[]{"pobi","crong","honux"}));
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 누적_회차_결과_NULL_예외() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    new Result(null);
                }
        );
    }
}
