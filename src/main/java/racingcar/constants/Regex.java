package racingcar.constants;

public enum Regex {
    COMMA(",");

    private final String regex;

    Regex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return this.regex;
    }
}
