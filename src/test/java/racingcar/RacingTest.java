package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.RoundStatus;

import static org.assertj.core.api.Assertions.*;

public class RacingTest {
    private Racing racing;

    @BeforeEach
    void setUp() {
        this.racing = new Racing("test1,test2,test3", 3);
    }

    @Test
    void 회차_세팅() {
        assertThatNoException()
                .isThrownBy(() -> {
                    this.racing
                            .getCar("test1")
                            .getRound(0);
                    this.racing
                            .getCar("test1")
                            .getRound(1);
                    this.racing
                            .getCar("test1")
                            .getRound(2);
                });
    }

    @Test
    void 회차_세팅_실패_회차범위_초과() {
        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> {
                    this.racing
                            .getCar("test1")
                            .getRound(3);
                });
    }

    @Test
    void 회차_세팅_실패_없는차_조회() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    this.racing
                            .getCar("test9")
                            .getRound(1);
                });
    }

    @Test
    void 회차_세팅_실패_null() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    this.racing
                            .getCar(null)
                            .getRound(1);
                });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            1, 2, 3, 4, 10
    })
    void 시도_회수_유효성_체크(int repeatRoundCount) {
        assertThatNoException()
                .isThrownBy(() -> {
                    this.racing = new Racing("test1,test2,test3", repeatRoundCount);
                });
    }

    @ParameterizedTest
    @ValueSource(ints = {
            0, -1, -2, -3, -4, -10
    })
    void 시도_회수_유효성_체크_예외처리(int repeatRoundCount) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    this.racing = new Racing("test1,test2,test3", repeatRoundCount);
                });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "kim,park,seok",
            "kim,park,seok,",
            "kim,park,seok, s s",
            "kim,park,seok,jang",
    }, delimiterString = ":")
    void 자동차_이름_유효성_체크(String names) {
        assertThatNoException()
                .isThrownBy(() -> {
                    this.racing = new Racing(names, 1);
                });
    }

    @ParameterizedTest
    @CsvSource(value = {
            "kim,parddk,seok",
            "kim,park,seok,jangaj"
    }, delimiterString = ":")
    void 자동차_이름_유효성_체크_예외처리(String names) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    this.racing = new Racing(names, 1);
                });
    }
}
