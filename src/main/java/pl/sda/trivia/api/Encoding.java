package pl.sda.trivia.api;

public enum Encoding {
    URL3986,
    BASE64;
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
