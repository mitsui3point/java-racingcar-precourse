package racingcar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.constant.RoundStatus;

import static org.assertj.core.api.Assertions.*;

public class CarTest {

    private Car car;

    @BeforeEach
    void setUp() {
        this.car = new Car("test1");
        this.car.addRound(new Round(3));
        this.car.addRound(new Round(4));
        this.car.addRound(new Round(5));
    }

    @Test
    void 회차_객체_세팅() {
        RoundStatus actual1 = this.car.getRound(0).getRoundStatus();
        RoundStatus actual2 = this.car.getRound(1).getRoundStatus();
        RoundStatus actual3 = this.car.getRound(2).getRoundStatus();
        RoundStatus expected1 = RoundStatus.STOP;
        RoundStatus expected2 = RoundStatus.FORWARD;
        RoundStatus expected3 = RoundStatus.FORWARD;
        assertThat(actual1).isEqualTo(expected1);
        assertThat(actual2).isEqualTo(expected2);
        assertThat(actual3).isEqualTo(expected3);
    }

    @Test
    void 회차_객체_세팅_예외처리() {
        this.car = new Car("test2");
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.car.addRound(null);
        });
    }

    @Test
    void 회차_객체_세팅_호출_예외처리() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.car.getRound(-1).getRoundStatus();
        });
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.car.getRound(-2).getRoundStatus();
        });
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.car.getRound(4).getRoundStatus();
        });
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            this.car.getRound(3).getRoundStatus();
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "kim", "park", "jessi",
            "KIM", "PARK", "JESSI",
            "321", "9876", "12345",
            "!@#$%", "^&*()", "\"[]{}", ";':|", ",.<>/", "?_+-="})
    void 자동차_이름_유효성_체크(String name) {
        assertThatNoException()
                .isThrownBy(() -> {
                    this.car = new Car(name);
                });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {
            "abcdef", "123456", "12 456"})
    void 자동차_이름_유효성_체크_예외처리(String name) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    this.car = new Car(name);
                });
    }
}
