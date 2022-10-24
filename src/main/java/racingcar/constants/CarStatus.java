package racingcar.constants;

public enum CarStatus {
    FORWARD, STOP;

    public static final int MIN_FORWARD_NUMBER = 4;

    public static boolean isForward(int randomNumber) {
        return randomNumber >= MIN_FORWARD_NUMBER;
    }

}
