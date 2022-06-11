package pl.sda.trivia.api;

public enum Category {
    MYTHOLOGY(20, "Mitologia"), //Category MYTHOLOGY = new Category(20, "Mitologia")
    SCIENCE(17, "Nauka"),
    SPORT(21, "Sport"),
    ANY(0,"");
    private final int id;
    private final String name;

    Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
