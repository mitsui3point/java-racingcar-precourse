package racingcar;

import java.util.ArrayList;
import java.util.List;

public class Car {
    public static final int MIN_ROUND_INDEX = 0;
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    private String name;
    private List<Round> rounds;

    public Car(String name) {
        this.checkMembers(name);
        this.setMembers(name);
    }

    private void checkMembers(String name) {
        if(isInvalidName(name)) {
            throw new IllegalArgumentException();
        }
    }

    private void setMembers(String name) {
        this.name = name;
        this.rounds = new ArrayList<>();
    }

    private boolean isInvalidName(String name) {
        return name == null ||
                name.length() < MIN_NAME_LENGTH ||
                name.length() > MAX_NAME_LENGTH;
    }

    public void addRound(Round round) {
        this.checkRound(round);
        this.rounds.add(round);
    }

    private void checkRound(Round round) {
        if(isInvalidRound(round)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isInvalidRound(Round round) {
        return round == null;
    }

    public Round getRound(int roundIndex) {
        this.checkRoundIndex(roundIndex);
        return this.rounds.get(roundIndex);
    }

    private void checkRoundIndex(int roundIndex) {
        if(isInvalidRoundIndex(roundIndex)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isInvalidRoundIndex(int roundIndex) {
        return roundIndex < MIN_ROUND_INDEX ||
                roundIndex > this.rounds.size();
    }

    public String getName() {
        return this.name;
    }
}
