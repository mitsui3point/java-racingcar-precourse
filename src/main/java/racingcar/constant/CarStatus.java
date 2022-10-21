package racingcar.constant;

public enum CarStatus {
    FORWARD, STOP;

    public static boolean isForward(CarStatus carStatus) {
        return CarStatus.FORWARD.equals(carStatus);
    }
}
