package pl.sda.trivia.api;

public enum Difficulty {
    EASY,
    MEDIUM,
    HARD;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
