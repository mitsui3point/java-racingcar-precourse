package racingcar.constants;

public enum CarStatus {
    FORWARD("-"), STOP("");

    public static final int MIN_FORWARD_NUMBER = 4;
    private final String print;

    CarStatus(String print) {
        this.print = print;
    }

    public String getPrint() {
        return print;
    }

    public static boolean isForwardNumber(int randomNumber) {
        return randomNumber >= MIN_FORWARD_NUMBER;
    }

    public static boolean isForward(CarStatus status) {
        return status.equals(FORWARD);
    }
}
