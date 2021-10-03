package pl.darbean.WarhammerServer.model.hero;

public class Hero {

    private long id;
    private int determinationPoints;
    private int heroPoints;
    private int destinationPoint;
    private int luckPoints;
    private String name;
    private String surname;
    private Race race;
    private Klass clazz;
    private Profession profession;
    private String professionLevel;
    private String status;
    private int age = 15;
    private String eyeColor;
    private String hairColor;
    private int height = 95;
    private int health;
    private int currentExp;
    private int totalExp;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameAndSurname() {
        return name + " " + surname;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public Klass getClazz() {
        return clazz;
    }

    public void setClazz(Klass clazz) {
        this.clazz = clazz;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public String getProfessionLevel() {
        return professionLevel;
    }

    public void setProfessionLevel(String professionLevel) {
        this.professionLevel = professionLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDeterminationPoints() {
        return determinationPoints;
    }

    public void setDeterminationPoints(int determinationPoints) {
        this.determinationPoints = determinationPoints;
    }

    public int getHeroPoints() {
        return heroPoints;
    }

    public void setHeroPoints(int heroPoints) {
        this.heroPoints = heroPoints;
    }

    public int getDestinationPoint() {
        return destinationPoint;
    }

    public void setDestinationPoint(int destinationPoint) {
        this.destinationPoint = destinationPoint;
    }

    public int getLuckPoints() {
        return luckPoints;
    }

    public void setLuckPoints(int luckPoints) {
        this.luckPoints = luckPoints;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(int currentExp) {
        this.currentExp = currentExp;
    }

    public int getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(int totalExp) {
        this.totalExp = totalExp;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "determinationPoints=" + determinationPoints +
                ", heroPoints=" + heroPoints +
                ", destinationPoint=" + destinationPoint +
                ", luckPoints=" + luckPoints +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", race=" + race +
                ", clazz=" + clazz +
                ", profession=" + profession +
                ", professionLevel='" + professionLevel + '\'' +
                ", status='" + status + '\'' +
                ", age=" + age +
                ", eyeColor='" + eyeColor + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", height=" + height +
                ", health=" + health +
                ", currentExp=" + currentExp +
                ", totalExp=" + totalExp +
                '}';
    }
}
