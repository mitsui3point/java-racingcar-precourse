package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Rounds {

    public static final int MIN_RANGE_ROUNDS_NUMBER = 1;
    private int roundsNumber;
    private List<Round> rounds;

    public Rounds(int roundsNumber) {
        this.checkMembers(roundsNumber);
        this.setMembers(roundsNumber);
    }

    private void setMembers(int roundsNumber) {
        this.setRoundsNumber(roundsNumber);
        this.addRounds(roundsNumber);
    }

    private void setRoundsNumber(int roundsNumber) {
        this.roundsNumber = roundsNumber;
    }

    private void addRounds(int roundsNumber) {
        this.rounds = new ArrayList<>();
        for (int roundIndex = 0; roundIndex < roundsNumber; roundIndex++) {
            this.rounds
                    .add(new Round(
                            Randoms.pickNumberInRange(
                                    Round.MIN_RANGE_RANDOM_NUMBER,
                                    Round.MAX_RANGE_RANDOM_NUMBER)));
        }
    }

    private void checkMembers(int roundsNumber) {
        if (!isValidRoundsNumber(roundsNumber)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValidRoundsNumber(int roundsNumber) {
        return roundsNumber >= MIN_RANGE_ROUNDS_NUMBER;
    }

    public List<Round> getRounds() {
        return this.rounds;
    }
}
