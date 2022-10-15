package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Car {

    public static final int MIN_RANGE_ROUNDS_NUMBER = 1;
    private List<CarStatus> carStatuses;

    public Car(int carStatusCount) {
        this.checkMembers(carStatusCount);
        this.setMembers(carStatusCount);
    }

    private void setMembers(int carStatusCount) {
        this.addRounds(carStatusCount);
    }

    private void addRounds(int carStatusCount) {
        this.carStatuses = new ArrayList<>();
        for (int carStatusIndex = 0; carStatusIndex < carStatusCount; carStatusIndex++) {
            this.carStatuses
                    .add(new CarStatus(
                            this.getRandomNumber()));
        }
    }

    private int getRandomNumber() {
        return Randoms.pickNumberInRange(
                CarStatus.MIN_RANGE_RANDOM_NUMBER,
                CarStatus.MAX_RANGE_RANDOM_NUMBER);
    }

    private void checkMembers(int carStatusCount) {
        if (!isValidRoundsNumber(carStatusCount)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidRoundsNumber(int carStatusCount) {
        return carStatusCount >= MIN_RANGE_ROUNDS_NUMBER;
    }

    public List<CarStatus> getCarStatuses() {
        return this.carStatuses;
    }
}
