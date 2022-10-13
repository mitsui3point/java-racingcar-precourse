package racingcar;

import racingcar.constant.RoundStatus;

public class Round {
    public static final int MIN_RANGE_RANDOM_NUMBER = 0;
    public static final int MAX_RANGE_RANDOM_NUMBER = 9;
    public static final int MIN_FORWARD_NUMBER = 4;
    private int randomNumber;
    private RoundStatus roundStatus;

    public Round(int randomNumber) {
        this.checkMembers(randomNumber);
        this.setMembers(randomNumber);
    }

    private void setMembers(int randomNumber) {
        this.randomNumber = randomNumber;
        this.setRoundStatus();
    }

    private void setRoundStatus() {
        this.roundStatus = RoundStatus.STOP;
        if(isForwardRandomNumber()) {
            this.roundStatus = RoundStatus.FORWARD;
        }
    }

    private boolean isForwardRandomNumber() {
        return this.randomNumber >= MIN_FORWARD_NUMBER;
    }

    private void checkMembers(int randomNumber) {
        if(isIllegalRandomNumber(randomNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isIllegalRandomNumber(int randomNumber) {
        return randomNumber < MIN_RANGE_RANDOM_NUMBER ||
                randomNumber > MAX_RANGE_RANDOM_NUMBER;
    }

    public RoundStatus getRoundStatus() {
        return this.roundStatus;
    }
}
