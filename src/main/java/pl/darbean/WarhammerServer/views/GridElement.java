package pl.darbean.WarhammerServer.views;

public class GridElement {
    private final String label;
    private final String[] values;

    public GridElement(String label, String... values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public String[] getValues() {
        return values;
    }
}
