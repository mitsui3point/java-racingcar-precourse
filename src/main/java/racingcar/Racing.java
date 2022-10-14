package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Racing {
    public static final String SPLIT_COMMA = ",";
    public static final int MIN_ROUND_COUNT = 1;
    private List<Car> cars;
    private int repeatRoundIndex;

    public Racing(String namesText, int repeatRoundIndex) {
        this.setMembers(namesText, repeatRoundIndex);
    }

    private void setMembers(String namesText, int repeatRoundIndex) {
        this.checkMembers(repeatRoundIndex);
        this.setRepeatRoundIndex(repeatRoundIndex);
        this.setCars(namesText);
    }

    private void checkMembers(int repeatRoundIndex) {
        if(this.isInvalidRoundIndex(repeatRoundIndex)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isInvalidRoundIndex(int repeatRoundCount) {
        return repeatRoundCount < MIN_ROUND_COUNT;
    }

    private void setRepeatRoundIndex(int repeatRoundIndex) {
        this.repeatRoundIndex = repeatRoundIndex;
    }

    private void setCars(String namesText) {
        this.cars = new ArrayList<>();
        this.convertNamesToCars(namesText);
    }

    private void convertNamesToCars(String namesText) {
        String[] names = namesText.split(SPLIT_COMMA);
        int nameIndex = 0;
        while (this.isReachToNamesLength(names, nameIndex)) {
            this.cars.add(
                    this.addRoundsToCar(names, nameIndex));
            nameIndex++;
        }
    }

    private Car addRoundsToCar(String[] names, int nameIndex) {
        Car car = new Car(names[nameIndex]);
        int roundNo = 0;
        while (this.isReachToRepeatRoundNo(roundNo)) {
            car.addRound(new Round(
                    Randoms.pickNumberInRange(0, 9)));
            roundNo++;
        }
        return car;
    }

    private boolean isReachToNamesLength(String[] names, int nameIndex) {
        return nameIndex < names.length;
    }

    private boolean isReachToRepeatRoundNo(int roundNo) {
        return roundNo < this.repeatRoundIndex;
    }

    public Car getCar(String name) {
        for (Car car: this.cars) {
            return this.searchOneCarByName(name, car);
        }
        throw new IllegalArgumentException();
    }

    private Car searchOneCarByName(String name, Car car) {
        if(isEqualsCarName(name, car)) {
            return car;
        }
        throw new IllegalArgumentException();
    }

    private boolean isEqualsCarName(String name, Car car) {
        return car.getName().equals(name);
    }
}
