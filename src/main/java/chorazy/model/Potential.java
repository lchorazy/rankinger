package chorazy.model;

public class Potential {

    private final int attack;
    private final int support;
    private final int defense;

    public Potential(int attack, int support, int defense) {
        this.attack = attack;
        this.support = support;
        this.defense = defense;
    }

    public int getAttack() {
        return attack;
    }

    public int getSupport() {
        return support;
    }

    public int getDefense() {
        return defense;
    }

    @Override
    public String toString() {
        return "Potential{" +
                "attack=" + attack +
                ", support=" + support +
                ", defense=" + defense +
                '}';
    }
}
