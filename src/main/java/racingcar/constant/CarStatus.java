package racingcar.constant;

import racingcar.Car;

public enum CarStatus {
    FORWARD, STOP;

    public static boolean isForward(Car car) {
        return CarStatus.FORWARD.equals(car.getStatus());
    }
}
