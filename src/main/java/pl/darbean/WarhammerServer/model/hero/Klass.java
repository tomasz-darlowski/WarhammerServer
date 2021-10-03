package pl.darbean.WarhammerServer.model.hero;

public enum Klass {
    UCZENI("Uczony"),
    MIESZCZANIE("Mieszczanin"),
    DWOREZANIE("Dworzanin"),
    POSPOLSTWO("Plebs"),
    WEDROWCY("Wędrowiec"),
    WODNIACY("Wodniak"),
    LOTRY("Łotr"),
    WOJOWNICY("Wojownik");

    private String label;

    Klass(String label) {
        this.label=label;
    }

    public String getLabel() {
        return label;
    }
}
