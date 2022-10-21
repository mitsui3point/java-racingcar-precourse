package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class RoundResultTest {
    private RoundResult roundResult;
    private List<Round> rounds;
    private int limitRoundIndex;
    private String names;
    private Map<String, String> expected;

    @BeforeEach
    void setUp() {
        this.names = "pobi,crong,honux";

        this.limitRoundIndex = 10;
        this.rounds = new ArrayList<>();
        for (int roundIndex = 0; roundIndex < this.limitRoundIndex; roundIndex++) {
            this.rounds.add(
                    new Round(
                            this.names,
                            this.getTestRandomNumbers(roundIndex)
                    )
            );
        }

        this.expected = new LinkedHashMap<>();
        this.expected.put("pobi", "-");
        this.expected.put("crong", "-----");
        this.expected.put("honux", "--");
    }

    @Test
    void 전체회차_저장() {
        this.roundResult = new RoundResult(this.rounds);
        LinkedHashMap<String, String> actual = this.roundResult.getRoundResult(4);
        System.out.println(actual);
        System.out.println(this.expected);
        assertThat(actual).isEqualTo(this.expected);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 전체회차_저장_예외(List<Round> rounds) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(
                        () -> this.roundResult = new RoundResult(rounds)
                );
    }

    private int[] getTestRandomNumbers(int roundIndex) {
        return new int[]{
                roundIndex % 10,
                (roundIndex + 4) % 10,
                (roundIndex + 8) % 10
        };
    }
}
