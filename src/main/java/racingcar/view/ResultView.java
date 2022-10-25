package racingcar.view;

import racingcar.Result;
import racingcar.constants.Print;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultView {
    private Result result;
    private StringBuilder printRoundResults;
    private String printWinners;

    public ResultView(Result result) {
        this.result = result;
        this.setPrintRoundResults();
        this.setPrintWinners();
    }

    private void setPrintWinners() {
        this.printWinners = Print.FINAL_ROUND_WINNER
                .concat(
                        String.join(
                                Print.COMMA_SPACE,
                                this.result.getWinners()
                        )
                );
    }

    private void setPrintRoundResults() {
        this.printRoundResults = new StringBuilder();
        for (LinkedHashMap<String, String> round : this.result.getRoundResults()) {
            for (Map.Entry<String, String> eachRound : round.entrySet()) {
                this.printRoundResults
                        .append(eachRound.getKey())
                        .append(Print.COLON)
                        .append(eachRound.getValue())
                        .append(Print.LINE_FEED);
            }
            this.printRoundResults.append(Print.LINE_FEED);
        }
    }

    public String getPrintRoundResults() {
        return this.printRoundResults.toString();
    }

    public String getPrintWinners() {
        return this.printWinners;
    }
}
