package racingcar;

import racingcar.constant.CarAction;

public class CarStatus {

    public static final int MIN_RANGE_RANDOM_NUMBER = 0;
    public static final int MAX_RANGE_RANDOM_NUMBER = 9;
    public static final int MIN_FORWARD_NUMBER = 4;
    private int randomNumber;

    public CarStatus(int randomNumber) {
        this.checkMembers(randomNumber);
        this.setMembers(randomNumber);
    }

    private void setMembers(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    private void checkMembers(int randomNumber) {
        if (!isValidRandomNumber(randomNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isForwardNumber() {
        return this.randomNumber >= MIN_FORWARD_NUMBER;
    }

    private boolean isValidRandomNumber(int randomNumber) {
        return randomNumber >= MIN_RANGE_RANDOM_NUMBER &&
                randomNumber <= MAX_RANGE_RANDOM_NUMBER;
    }

    public CarAction getCarAction() {
        if (isForwardNumber()) {
            return CarAction.FORWARD;
        }
        return CarAction.STOP;
    }
}
