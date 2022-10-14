package racingcar;

import org.assertj.core.api.AbstractIterableAssert;

import java.util.HashMap;
import java.util.Map;

public class Cars {
    public static final String COMMA = ",";
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    public static final int MIN_NAMES_LENGTH = 1;
    private Map<String, Car> cars;

    public Cars(String names, int roundNumber) {
        this.checkMembers(names);
        this.setMembers(names, roundNumber);
    }

    private void setMembers(String names, int roundNumber) {
        this.cars = new HashMap<>();
        for (String name: names.split(COMMA)) {
            this.cars.put(name, new Car(roundNumber));
        }
    }

    private void checkMembers(String names) {
        this.checkInvalidMembers(this.isValidNamesNotNull(names));
        this.checkInvalidMembers(this.isValidNamesLength(names));
        for (String name: names.split(COMMA)) {
            this.checkInvalidMembers(this.isValidNameLength(name));
        }
    }

    private void checkInvalidMembers(boolean validCondition) {
        if (!validCondition) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidNameLength(String name) {
        return name.length() >= MIN_NAME_LENGTH &&
                name.length() <= MAX_NAME_LENGTH;
    }

    private boolean isValidNamesLength(String names) {
        return names.split(COMMA).length >= MIN_NAMES_LENGTH;
    }

    private boolean isValidNamesNotNull(String names) {
        return names != null;
    }

    public Map<String, Car> getCars() {
        return this.cars;
    }
}