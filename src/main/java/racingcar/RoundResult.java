package racingcar;

import racingcar.constant.CarStatus;
import racingcar.constant.ErrorMessage;
import racingcar.constant.Result;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RoundResult {
    private List<LinkedHashMap<String, String>> roundResults;
    private LinkedHashMap<String, String> roundResult;

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
        this.roundResult = new LinkedHashMap<>();
        this.roundResults = new ArrayList<>();
        for (Round round:rounds) {
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

    public LinkedHashMap<String, String> getRoundResult(int roundIndex) {
        return this.roundResults.get(roundIndex);
    }
}
