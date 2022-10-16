package racingcar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RacingResult {
    private Rounds rounds;

    public RacingResult(Rounds rounds) {
        this.checkMembers(rounds);
        this.rounds = rounds;
    }

    private void checkMembers(Rounds rounds) {
        if(rounds == null) {
            throw new IllegalArgumentException();
        }
    }

    public int getMaxForwardCount() {
        int maxForwardCount = 0;
        for (List<String> roundCarStatuses:
                this.rounds
                        .getRounds()
                        .values()
        ) {
            maxForwardCount = getCarFinalForwardCount(maxForwardCount, roundCarStatuses);
        }
        return maxForwardCount;
    }

    private int getCarFinalForwardCount(int maxForwardCount, List<String> roundCarStatuses) {
        if (isGreaterThanLastMaxValue(maxForwardCount, roundCarStatuses)) {
            maxForwardCount = roundCarStatuses.get(
                    roundCarStatuses.size() - 1
            ).length();
        }
        return maxForwardCount;
    }

    private boolean isGreaterThanLastMaxValue(int maxForwardCount, List<String> roundCarStatuses) {
        return maxForwardCount < roundCarStatuses.get(
                roundCarStatuses.size() - 1
        ).length();
    }

    public List<String> getRoundWinners() {
        Set<String> cars = this.rounds.getRounds().keySet();
        List<String> winners = new ArrayList<>();
        for (String name: cars) {
            List<String> roundForwards =  this.rounds.getRounds().get(name);
            if(roundForwards.get(roundForwards.size() - 1).length() == this.getMaxForwardCount()) {
                winners.add(name);
            }
        }
        return winners;
    }
}
