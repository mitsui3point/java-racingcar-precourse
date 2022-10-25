package racingcar.util;

import org.junit.jupiter.api.RepeatedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class RandomNumberGeneratorTest {
    private RandomNumberGenerator randomNumberGenerator;
    @RepeatedTest(30)
    void 랜덤번호_생성() {
        this.randomNumberGenerator = new RandomNumberGenerator();
        int actual = this.randomNumberGenerator.generateNumber();
        assertThat(actual).isBetween(0, 9);
    }
}
