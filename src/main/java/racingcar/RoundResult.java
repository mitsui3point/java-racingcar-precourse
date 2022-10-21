package racingcar;

import racingcar.constant.CarStatus;
import racingcar.constant.ErrorMessage;
import racingcar.constant.Result;

import java.util.*;

public class RoundResult {
    private List<LinkedHashMap<String, String>> roundResults;
    private LinkedHashMap<String, String> roundResult;
    private List<String> winners;

    public RoundResult(List<Round> rounds) {
        this.checkMembers(rounds);
        this.setMembers(rounds);
    }

    private void checkMembers(List<Round> rounds) {
        if (!this.isValidRounds(rounds)) {
            throw new IllegalArgumentException(ErrorMessage.ROUNDS_IS_EMPTY);
        }
    }

    private boolean isValidRounds(List<Round> rounds) {
        return rounds != null &&
                !rounds.isEmpty();
    }

    private void setMembers(List<Round> rounds) {
        this.addRoundResults(rounds);
        this.setWinners();
    }

    private void addRoundResults(List<Round> rounds) {
        this.roundResult = new LinkedHashMap<>();
        this.roundResults = new ArrayList<>();
        for (Round round: rounds) {
            this.putRoundResult(round);
            this.roundResults.add(new LinkedHashMap<>(this.roundResult));
        }
    }

    private void putRoundResult(Round round) {
        for (Map.Entry<String, CarStatus> roundCar :
                round.getRound().entrySet()) {
            String carName = roundCar.getKey();
            this.roundResult.put(
                    carName,
                    this.getAccumulateForward(
                            carName,
                            this.getPrintForward(roundCar.getValue())
                    )
            );
        }
    }

    private String getAccumulateForward(String carName, String printForward) {
        if (this.roundResult.containsKey(carName)) {
            return this.roundResult.get(carName)
                    .concat(printForward);
        }
        return Result.PRINT_STOP
                .concat(printForward);
    }

    private String getPrintForward(CarStatus roundCar) {
        if (CarStatus.isForward(roundCar)) {
            return Result.PRINT_FORWARD;
        }
        return Result.PRINT_STOP;
    }

    public List<LinkedHashMap<String, String>> getRoundResult() {
        return this.roundResults;
    }

    public List<String> getWinners() {
        return this.winners;
    }

    private void setWinners() {
        this.winners = new ArrayList<>();
        this.addWinner(this.getWinnerScore());
    }

    private void addWinner(int winnerScore) {
        for (Map.Entry<String, String> car : getLastRoundEntrySet()) {
            String player = car.getKey();
            int playerScore = car.getValue().length();
            if (this.isWinner(winnerScore, playerScore)) {
                this.winners.add(player);
            }
        }
    }

    private boolean isWinner(int winnerScore, int playerScore) {
        return winnerScore == playerScore;
    }

    private int getWinnerScore() {
        int winnerScore = 0;
        for (Map.Entry<String, String> car : getLastRoundEntrySet()) {
            String player = car.getKey();
            int playerScore = car.getValue().length();
            if (this.isWinnerScore(winnerScore, playerScore)){
                winnerScore = playerScore;
            }
        }
        return winnerScore;
    }

    private Set<Map.Entry<String, String>> getLastRoundEntrySet() {
        return this.roundResults.get(this.roundResults.size() - 1).entrySet();
    }

    private boolean isWinnerScore(int winnerScore, int playerScore) {
        return winnerScore < playerScore;
    }
}
