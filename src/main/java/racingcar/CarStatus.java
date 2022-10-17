package racingcar;

import racingcar.constant.Conditions;
import racingcar.constant.ErrorMessage;
import racingcar.constant.Status;

public class CarStatus {
    private int randomNumber;

    public CarStatus(int randomNumber) {
        if (this.isValidRangeRandomNumber(randomNumber)) {
            throw new IllegalArgumentException(ErrorMessage.RANDOM_NUMBER_IS_OUT_OF_RANGE);
        }
        this.randomNumber = randomNumber;
    }

    private boolean isValidRangeRandomNumber(int randomNumber) {
        return randomNumber >= Conditions.MIN_RANDOM_NUMBER &&
                randomNumber <= Conditions.MAX_RANDOM_NUMBER;
    }

    public Status get() {
        if (this.isForwardNumber()) {
            return Status.FORWARD;
        }
        return Status.STOP;
    }

    private boolean isForwardNumber() {
        return this.randomNumber >= Conditions.MIN_FORWARD_NUMBER;
    }
}
