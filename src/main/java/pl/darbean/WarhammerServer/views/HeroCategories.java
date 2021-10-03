package pl.darbean.WarhammerServer.views;

public enum HeroCategories {

    BASIC_DATA("Dosier"),
    CECHY("Cechy"),
    BASIC_SKILL("Umiejętności podstawowe"),
    ADVENCED_SKILLS("Umiejętności zaawansowane"),
    TALENTS("Talenty"),
    ARMOR("Zbroja"),
    WEAPON("Broń"),
    INVENTORY("Plecak");

    private final String label;

    HeroCategories(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
