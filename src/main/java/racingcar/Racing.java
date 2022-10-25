package racingcar;

import camp.nextstep.edu.missionutils.Console;
import racingcar.constants.Print;
import racingcar.util.RandomNumberGenerator;
import racingcar.view.InputView;
import racingcar.view.ResultView;

public class Racing {

    public static void run() {
        InputView inputView = new InputView();
        Result result = new Result(
                new Cars(
                        requestPutNames(inputView),
                        requestFinalRoundIndex(inputView),
                        new RandomNumberGenerator()
                )
        );
        ResultView resultView = new ResultView(result);
        System.out.println(resultView.getPrintRoundResults() + resultView.getPrintWinners());
    }

    private static int requestFinalRoundIndex(InputView inputView) {
        try {
            System.out.println(Print.REQUEST_FINAL_ROUND_INDEX);
            inputView.setFinalRoundIndex(Console.readLine());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            requestFinalRoundIndex(inputView);
        }
        return inputView.getFinalRoundIndex();
    }

    private static String requestPutNames(InputView inputView) {
        try {
            System.out.println(Print.REQUEST_PUT_NAMES);
            inputView.setNames(Console.readLine());
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            requestPutNames(inputView);
        }
        return inputView.getNames();
    }
}
