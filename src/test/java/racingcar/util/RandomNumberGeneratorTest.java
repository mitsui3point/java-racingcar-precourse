package racingcar.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.Conditions;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberGeneratorTest {
    private RandomNumberGenerator randomNumberGenerator;

    @ParameterizedTest
    @ValueSource(ints = {3, 4, 10})
    void 랜덤_번호_목록_생성(int playersCount) {
        this.randomNumberGenerator = new RandomNumberGenerator(playersCount);
        int actual = this.randomNumberGenerator.generateNumbers().length;
        assertThat(actual).isEqualTo(playersCount);
    }

    @Test
    void 랜덤_번호_목록_생성() {
        this.randomNumberGenerator = new RandomNumberGenerator(10);
        int[] actuals = this.randomNumberGenerator.generateNumbers();
        for (int actual: actuals) {
            assertThat(actual).isBetween(
                    Conditions.MIN_RANDOM_NUMBER,
                    Conditions.MAX_RANDOM_NUMBER);
        }
    }
}
