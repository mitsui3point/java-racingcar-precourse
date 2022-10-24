package racingcar.constants;

import racingcar.util.NumberGenerator;

public class Conditions {
    public static final int MIN_NAMES_LENGTH = 0;
    public static final int MIN_NAME_LENGTH = 1;
    public static final int MAX_NAME_LENGTH = 5;
    public static final int MIN_FINAL_ROUND_INDEX = 0;

    public static boolean isValidName(String name) {
        return name.trim().length() >= MIN_NAME_LENGTH &&
                name.trim().length() <= MAX_NAME_LENGTH;
    }

    public static boolean isValidNames(String names) {
        return names != null &&
                !names.isEmpty() &&
                names.split(Regex.COMMA.getRegex()).length > MIN_NAMES_LENGTH;
    }

    public static boolean isFinalRoundIndex(int finalRoundIndex) {
        return finalRoundIndex > MIN_FINAL_ROUND_INDEX;
    }

    public static boolean isValidNumberGenerator(NumberGenerator numberGenerator) {
        return numberGenerator != null;
    }
}
