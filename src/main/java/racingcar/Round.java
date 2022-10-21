package racingcar;

import racingcar.constant.CarStatus;
import racingcar.constant.Conditions;
import racingcar.constant.ErrorMessage;
import racingcar.constant.Regex;
import racingcar.util.NumberGenerator;

import java.util.LinkedHashMap;
import java.util.Map;

public class Round {
    private Map<String, CarStatus> round;

    public Round(String names, NumberGenerator numberGenerator) {
        this.checkMembers(names, numberGenerator);
        this.setMembers(names, numberGenerator);
    }

    private void checkMembers(String names, NumberGenerator numberGenerator) {
        this.checkMember(this.isValidNames(names),
                ErrorMessage.CAR_NAMES_IS_EMPTY);
        for (String name : names.split(Regex.COMMA)) {
            this.checkMember(this.isValidNameLength(name),
                    ErrorMessage.CAR_NAME_LENGTH_IS_OUT_OF_RANGE);
        }
        this.checkMember(this.isValidNumberGenerator(numberGenerator),
                ErrorMessage.NUMBER_GENERATOR_IS_NULL);
        this.checkMember(this.isValidRandomNumbersLength(numberGenerator.generateNumber(), names),
                ErrorMessage.RANDOM_NUMBERS_LENGTH_IS_INVALID);
    }

    private void checkMember(boolean checkCondition, String errorMessage) {
        if (!checkCondition) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private boolean isValidNumberGenerator(NumberGenerator numberGenerator) {
        return numberGenerator != null;
    }

    private boolean isValidRandomNumbersLength(int[] randomNumbers, String names) {
        return randomNumbers != null &&
                randomNumbers.length == names.split(Regex.COMMA).length;
    }

    private boolean isValidNameLength(String name) {
        String trimmedName = name.trim();
        return trimmedName.length() >= Conditions.MIN_NAME_LENGTH &&
                trimmedName.length() <= Conditions.MAX_NAME_LENGTH;
    }

    private boolean isValidNames(String names) {
        return names != null &&
                names.replaceAll(Regex.COMMA, Regex.EMPTY_TEXT)
                        .trim()
                        .length() > Conditions.MIN_NAMES_LENGTH &&
                !names.replaceAll(Regex.COMMA, Regex.EMPTY_TEXT)
                        .trim()
                        .isEmpty();
    }

    private void setMembers(String names, NumberGenerator numberGenerator) {
        this.round = new LinkedHashMap<>();
        int carCount = 0;
        for (String name : names.split(Regex.COMMA)) {
            this.round.put(
                            name,
                            new Car(numberGenerator.generateNumber()[carCount++]).getStatus()
            );
        }
    }

    public Map<String, CarStatus> getRound() {
        return this.round;
    }
}