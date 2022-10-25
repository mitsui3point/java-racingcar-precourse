package racingcar.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constants.ErrorMessage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class InputViewTest {

    private InputView inputView;
    private String names;
    private String finalRoundIndexText;

    @BeforeEach
    void setUp() {
        this.names = "pobi,crong,honux";
        this.finalRoundIndexText = "5";
    }

    @Test
    void 이름목록_등록_호출_비교() {
        this.inputView = new InputView(this.names, this.finalRoundIndexText);
        String actual = this.inputView.getNames();
        assertThat(actual).isEqualTo(this.names);
    }

    @Test
    void 회차_등록_호출_비교() {
        this.inputView = new InputView(this.names, this.finalRoundIndexText);
        int actual = this.inputView.getFinalRoundIndex();
        assertThat(actual).isEqualTo(Integer.parseInt(this.finalRoundIndexText));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 이름목록입력_예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    new InputView(names, this.finalRoundIndexText);
                }
        ).withMessageContaining(ErrorMessage.NAMES_IS_NULL.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "pobi,javaji",
            "pobi,java i"
    })
    void 이름입력_예외(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    new InputView(names, this.finalRoundIndexText);
                }
        ).withMessageContaining(ErrorMessage.NAME_IS_OUT_OF_RANGE.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0", "-1", "-10", "-24"
    })
    void 회차_예외(String finalRoundIndex) {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(
                () -> {
                    new InputView(this.names, finalRoundIndex);
                }
        ).withMessageContaining(ErrorMessage.FINAL_ROUND_INDEX_IS_OUT_OF_RANGE.getMessage());
    }
}
