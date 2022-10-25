package racingcar.view;

import racingcar.constants.Conditions;
import racingcar.constants.ErrorMessage;
import racingcar.constants.Regex;

public class InputView {
    private String names;
    private int finalRoundIndex;

    public void setFinalRoundIndex(String finalRoundIndexText) {
        if (!Conditions.isValidCastableFinalRoundIndex(finalRoundIndexText)) {
            throw new IllegalArgumentException(ErrorMessage.FINAL_ROUND_INDEX_IS_NOT_A_NUMBER.getMessage());
        }
        int finalRoundIndex = Integer.parseInt(finalRoundIndexText);
        if (!Conditions.isValidRangeFinalRoundIndex(finalRoundIndex)) {
            throw new IllegalArgumentException(ErrorMessage.FINAL_ROUND_INDEX_IS_OUT_OF_RANGE.getMessage());
        }
        this.finalRoundIndex = finalRoundIndex;
    }

    public String getNames() {
        return this.names;
    }

    public void setNames(String names) {
        if (!Conditions.isValidNames(names)) {
            throw new IllegalArgumentException(ErrorMessage.NAMES_IS_NULL.getMessage());
        }
        for (String name :
                names.split(Regex.COMMA.getRegex())) {
            if (!Conditions.isValidName(name)) {
                throw new IllegalArgumentException(ErrorMessage.NAME_IS_OUT_OF_RANGE.getMessage());
            }
        }
        this.names = names;
    }

    public int getFinalRoundIndex() {
        return this.finalRoundIndex;
    }
}
