package pl.darbean.WarhammerServer.model.pouch;

public class Pouch {

    int gold;
    int silver;
    int pens;

    public Pouch() {
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public String getGoldLabel() {
        return gold + " ZK";
    }

    public int getSilver() {
        return silver;
    }

    public void setSilver(int silver) {
        this.silver = silver;
    }

    public String getSilverLabel() {
        return silver + " SZ";
    }

    public int getPens() {
        return pens;
    }

    public void setPens(int pens) {
        this.pens = pens;
    }

    public String getPensLabel() {
        return pens + " P";
    }

    public int getTotalPens() {
        return (20 * 12 * gold) + (12 * silver) + pens;
    }

    public void addMoney(int gold, int silver, int pens) {
        int addPens = (20 * 12 * gold) + (12 * silver) + pens;
        int currentPens = getTotalPens();
        currentPens += addPens;

        setPouch(currentPens);
    }

    public boolean spentMoney(int gold, int silver, int pens) {
        int minusPens = (20 * 12 * gold) + (12 * silver) + pens;
        int currentPens = getTotalPens();
        currentPens -= minusPens;
        if (currentPens < 0) {
            return false;
        }
        setPouch(currentPens);
        return true;
    }

    private void setPouch(int currentPens) {
        this.pens = currentPens % 12;
        int currentSilver = (currentPens - this.pens) / 12;
        this.silver = currentSilver % 20;
        this.gold = (currentSilver - this.silver) / 20;
    }
}
