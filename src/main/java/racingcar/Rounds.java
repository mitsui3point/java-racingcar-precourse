package racingcar;

import racingcar.constant.CarAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rounds {
    public static final String COMMA = ",";
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    public static final int MIN_NAMES_LENGTH = 1;
    public static final String FORWARD_ONE = "-";
    private Map<String, List<String>> rounds;

    public Rounds(String names, int roundNumber) {
        this.checkMembers(names);
        this.setMembers(names, roundNumber);
    }

    private void setMembers(String names, int roundNumber) {
        this.rounds = new HashMap<>();
        for (String name: names.split(COMMA)) {
            rounds.put(name, getRoundForwards(roundNumber));
        }
    }

    private List<String> getRoundForwards(int roundNumber) {
        List<String> roundForwards = new ArrayList<>();
        StringBuilder forward = new StringBuilder();
        for (CarStatus carStatus: new Car(roundNumber).getCarStatuses()) {
            roundForwards.add(sumForwards(forward, carStatus));
        }
        return roundForwards;
    }

    private String sumForwards(StringBuilder forward, CarStatus status) {
        if(isForwardAction(status)) {
            forward.append(FORWARD_ONE);
        }
        return forward.toString();
    }

    private boolean isForwardAction(CarStatus status) {
        return status.getCarAction() == CarAction.FORWARD;
    }

    private void checkMembers(String names) {
        checkInvalidMembers(isValidNamesNotNull(names));
        checkInvalidMembers(isValidNamesLength(names));
        for (String name: names.split(COMMA)) {
            checkInvalidMembers(isValidNameLength(name));
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

    public Map<String, List<String>> getRounds() {
        return this.rounds;
    }
}