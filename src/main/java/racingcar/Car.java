package racingcar;

import racingcar.constant.Conditions;
import racingcar.constant.ErrorMessage;
import racingcar.constant.CarStatus;

public class Car {
    private int randomNumber;

    public Car(int randomNumber) {
        if (!this.isValidRangeRandomNumber(randomNumber)) {
            throw new IllegalArgumentException(ErrorMessage.RANDOM_NUMBER_IS_OUT_OF_RANGE);
        }
        this.randomNumber = randomNumber;
    }

    private boolean isValidRangeRandomNumber(int randomNumber) {
        return randomNumber >= Conditions.MIN_RANDOM_NUMBER &&
                randomNumber <= Conditions.MAX_RANDOM_NUMBER;
    }

    public CarStatus getStatus() {
        if (this.isForwardNumber()) {
            return CarStatus.FORWARD;
        }
        return CarStatus.STOP;
    }

    private boolean isForwardNumber() {
        return this.randomNumber >= Conditions.MIN_FORWARD_NUMBER;
    }
}
