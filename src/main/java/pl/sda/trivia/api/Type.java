package pl.sda.trivia.api;

public enum Type {
    MULTIPLE_CHOICE("multiple"),
    TRUE_FALSE("boolean"),
    ANY("");

    private final String queryValue;

    Type(String queryValue) {
        this.queryValue = queryValue;
    }

    @Override
    public String toString() {
        return this.queryValue;
    }
}
