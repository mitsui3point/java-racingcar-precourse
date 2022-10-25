package racingcar.util;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.constants.Conditions;

public class RandomNumberGenerator implements NumberGenerator {

    @Override
    public int generateNumber() {
        return Randoms.pickNumberInRange(Conditions.MIN_RANDOM_NUMBER, Conditions.MAX_RANDOM_NUMBER);
    }
}
