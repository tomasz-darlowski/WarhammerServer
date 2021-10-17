package pl.darbean.WarhammerServer.views;

public class FighterFormModel {
    String type;
    int quantity;
    int baseHealth;
    int randomizer;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public int getRandomizer() {
        return randomizer;
    }

    public void setRandomizer(int randomizer) {
        this.randomizer = randomizer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
