package racingcar.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import racingcar.Cars;
import racingcar.Result;
import racingcar.constants.CarStatus;
import racingcar.util.NumberGenerator;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultViewTest {
    private ResultView resultView;
    private Cars cars;
    private Result result;

    @BeforeEach
    void setUp() {
        this.cars = new Cars("pobi,crong,honux", 4, new NumberGenerator() {
            @Override
            public int generateNumber() {
                return CarStatus.MIN_FORWARD_NUMBER;
            }
        });
        this.result = new Result(this.cars);
    }

    @Test
    void 회차별_출력() {
        this.resultView = new ResultView(this.result);
        String actual = resultView.getPrintRoundResults();
        String expected = new StringBuilder()
                .append("pobi : -\n")
                .append("crong : -\n")
                .append("honux : -\n")
                .append("\n")
                .append("pobi : --\n")
                .append("crong : --\n")
                .append("honux : --\n")
                .append("\n")
                .append("pobi : ---\n")
                .append("crong : ---\n")
                .append("honux : ---\n")
                .append("\n")
                .append("pobi : ----\n")
                .append("crong : ----\n")
                .append("honux : ----\n")
                .append("\n")
                .toString();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 우승자_출력() {
        this.resultView = new ResultView(this.result);
        String actual = resultView.getPrintWinners();
        String expected = "최종 우승자 : pobi, crong, honux";
        assertThat(actual).isEqualTo(expected);
    }
}
