package racingcar;

import racingcar.constants.CarStatus;
import racingcar.constants.ErrorMessage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Result {
    private List<LinkedHashMap<String, String>> roundResults;
    private LinkedHashMap<String, String> round;

    public Result(Cars cars) {
        this.addRoundResults(cars);
    }

    private void addRoundResults(Cars cars) {
        if (!(cars != null)) {
            throw new IllegalArgumentException(ErrorMessage.CARS_IS_NULL.getMessage());
        }
        int finalRoundIndex = cars.getFinalRoundIndex();
        this.roundResults = new ArrayList<>();
        this.round = new LinkedHashMap<>();
        for (int roundIndex = 0; roundIndex < finalRoundIndex; roundIndex++) {
            this.putRound(cars, roundIndex);
            this.roundResults.add(new LinkedHashMap(this.round));
        }
    }

    private void putRound(Cars cars, int roundIndex) {
        for (Map.Entry<String, List<CarStatus>> car : cars.getCars().entrySet()) {
            String carName = car.getKey();
            CarStatus carStatus = car.getValue().get(roundIndex);
            this.round.put(carName, this.getPrintForwards(carName, this.getPrintForward(carStatus)));
        }
    }

    private String getPrintForwards(String carName, String printForward) {
        if (this.round.containsKey(carName)) {
            return this.round.get(carName).concat(printForward);
        }
        return printForward;
    }

    private String getPrintForward(CarStatus carStatus) {
        if (CarStatus.isForward(carStatus)) {
            return CarStatus.FORWARD.getPrint();
        }
        return CarStatus.STOP.getPrint();
    }

    public List<LinkedHashMap<String, String>> getRoundResults() {
        return roundResults;
    }

    public List<String> getWinners() {
        LinkedHashMap<String, String> lastRoundResult = this.roundResults.get(this.roundResults.size() - 1);
        int winnerScore = this.getWinnerScore(lastRoundResult);
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, String> lastRound : lastRoundResult.entrySet()) {
            int lastRoundCarScore = lastRound.getValue().length();
            if (this.isWinnerPlayer(winnerScore, lastRoundCarScore)) {
                winners.add(lastRound.getKey());
            }
        }
        return winners;
    }

    private boolean isWinnerPlayer(int winnerScore, int lastRoundCarScore) {
        return winnerScore == lastRoundCarScore;
    }

    private int getWinnerScore(LinkedHashMap<String, String> lastRoundResult) {
        int winnerScore = 0;
        for (Map.Entry<String, String> lastRound : lastRoundResult.entrySet()) {
            int lastRoundCarScore = lastRound.getValue().length();
            if (this.isWinnerScore(winnerScore, lastRoundCarScore)) {
                winnerScore = lastRoundCarScore;
            }
        }
        return winnerScore;
    }

    private boolean isWinnerScore(int winnerScore, int lastRoundCarScore) {
        return winnerScore <= lastRoundCarScore;
    }
}
