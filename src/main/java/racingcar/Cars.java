package racingcar;

import racingcar.constants.CarStatus;
import racingcar.constants.Conditions;
import racingcar.constants.ErrorMessage;
import racingcar.constants.Regex;
import racingcar.util.NumberGenerator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Cars {

    private String[] names;
    private int finalRoundIndex;
    private NumberGenerator numberGenerator;
    private LinkedHashMap<String, List<CarStatus>> cars;

    public Cars(String names, int finalRoundIndex, NumberGenerator numberGenerator) {
        this.setMembers(names, finalRoundIndex, numberGenerator);
    }

    private void setMembers(String names, int finalRoundIndex, NumberGenerator numberGenerator) {
        this.setNames(names);
        this.setFinalRoundIndex(finalRoundIndex);
        this.setNumberGenerator(numberGenerator);
        this.setCars(finalRoundIndex, numberGenerator);
    }

    private void setNames(String names) {
        if (!Conditions.isValidNames(names)) {
            throw new IllegalArgumentException(ErrorMessage.NAMES_IS_NULL.getMessage());
        }
        for (String name : names.split(Regex.COMMA.getRegex())) {
            if (!Conditions.isValidName(name)) {
                throw new IllegalArgumentException(ErrorMessage.NAME_IS_OUT_OF_RANGE.getMessage());
            }
        }
        this.names = names.split(Regex.COMMA.getRegex());
    }

    private void setFinalRoundIndex(int finalRoundIndex) {
        if (!Conditions.isValidRangeFinalRoundIndex(finalRoundIndex)) {
            throw new IllegalArgumentException(ErrorMessage.FINAL_ROUND_INDEX_IS_OUT_OF_RANGE.getMessage());
        }
        this.finalRoundIndex = finalRoundIndex;
    }

    private void setNumberGenerator(NumberGenerator numberGenerator) {
        if (!Conditions.isValidNumberGenerator(numberGenerator)) {
            throw new IllegalArgumentException(ErrorMessage.NUMBER_GENERATOR_IS_NULL.getMessage());
        }
        this.numberGenerator = numberGenerator;
    }

    private void setCars(int finalRoundIndex, NumberGenerator numberGenerator) {
        this.cars = new LinkedHashMap<>();
        this.putCars(finalRoundIndex, numberGenerator);
    }

    private void putCars(int finalRoundIndex, NumberGenerator numberGenerator) {
        for (String name : this.names) {
            this.addStatuses(finalRoundIndex, name, numberGenerator);
        }
    }

    private void addStatuses(int finalRoundIndex, String name, NumberGenerator numberGenerator) {
        List<CarStatus> statuses = new ArrayList<>();
        for (int roundIndex = 0; roundIndex < finalRoundIndex; roundIndex++) {
            statuses.add(new Car(numberGenerator.generateNumber()).getCarStatus());
        }
        this.cars.put(name, statuses);
    }

    public LinkedHashMap<String, List<CarStatus>> getCars() {
        return this.cars;
    }

    public int getFinalRoundIndex() {
        return this.finalRoundIndex;
    }
}
