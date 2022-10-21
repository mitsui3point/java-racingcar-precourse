package racingcar.util;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.constant.Conditions;

public class RandomNumberGenerator implements NumberGenerator {
    private int playersCount;
    private int[] randomNumbers;

    public RandomNumberGenerator(int playersCount) {
        this.playersCount = playersCount;
    }

    @Override
    public int[] generateNumbers() {
        this.randomNumbers = new int[this.playersCount];
        for (int generateIndex = 0; generateIndex < this.playersCount; generateIndex++) {
            this.randomNumbers[generateIndex] = Randoms.pickNumberInRange(
                    Conditions.MIN_RANDOM_NUMBER,
                    Conditions.MAX_RANDOM_NUMBER);
        }
        return this.randomNumbers;
    }
}
