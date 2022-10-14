package racingcar;

import racingcar.constant.RoundStatus;

public class Round {

    public static final int MIN_RANGE_RANDOM_NUMBER = 0;
    public static final int MAX_RANGE_RANDOM_NUMBER = 9;
    public static final int MIN_FORWARD_NUMBER = 4;
    private int randomNumber;

    public Round(int randomNumber) {
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

    private boolean isValidRandomNumber(int randomNumber) {
        return randomNumber >= MIN_RANGE_RANDOM_NUMBER &&
                randomNumber <= MAX_RANGE_RANDOM_NUMBER;
    }

    public RoundStatus getRoundStatus() {
        if (isForwardNumber()) {
            return RoundStatus.FORWARD;
        }
        return RoundStatus.STOP;
    }

    private boolean isForwardNumber() {
        return this.randomNumber >= MIN_FORWARD_NUMBER;
    }
}
