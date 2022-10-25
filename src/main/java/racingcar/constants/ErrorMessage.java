package racingcar.constants;

public enum ErrorMessage {
    NAMES_IS_NULL("이름이 비어있습니다."), NAME_IS_OUT_OF_RANGE("이름의 길이가 적절하지 않습니다."), FINAL_ROUND_INDEX_IS_OUT_OF_RANGE("횟수는 양의 정수만 입력이 가능합니다."), NUMBER_GENERATOR_IS_NULL("랜덤번호 생성에 실패하였습니다."), CARS_IS_NULL("결과를 도출할 정보가 부족합니다."), FINAL_ROUND_INDEX_IS_NOT_A_NUMBER("회차입력은 정수만 가능합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
