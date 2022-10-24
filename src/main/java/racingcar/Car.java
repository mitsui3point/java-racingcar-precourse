package racingcar;

import racingcar.constants.CarStatus;

public class Car {
    private int randomNumber;

    public Car(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public CarStatus getCarStatus() {
        if (CarStatus.isForward(this.randomNumber)) {
            return CarStatus.FORWARD;
        }
        return CarStatus.STOP;
    }
}
