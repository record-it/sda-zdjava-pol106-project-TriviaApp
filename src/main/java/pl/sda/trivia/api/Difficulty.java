package pl.sda.trivia.api;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD,
    ANY;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
