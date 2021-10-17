package pl.darbean.WarhammerServer.views.viewModel;

public class Fighter implements Comparable<Fighter> {

    String name;
    int initiative;
    int health;
    String description;
    boolean boss;
    boolean isPlayer;
    int playerFighterNumber;

    public Fighter() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isBoss() {
        return boss;
    }

    public void setBoss(boolean boss) {
        this.boss = boss;
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean player) {
        isPlayer = player;
    }

    public int getPlayerFighterNumber() {
        return playerFighterNumber;
    }

    public void setPlayerFighterNumber(int playerFighterNumber) {
        this.playerFighterNumber = playerFighterNumber;
    }

    @Override
    public int compareTo(Fighter o) {
        return Integer.compare(this.initiative, o.initiative);
    }
}
