package pulson.DesignPatterns.behavioral.chain_of_responsibility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Creature {
    public String name;
    public int attack, defense;
}

@AllArgsConstructor
class CreatureModifier {
    protected CreatureModifier next;
    protected Creature creature;

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    //Dodawanie do łańcucha
    public void add(CreatureModifier nextCreatureModifier) {
        if(next != null) {
            next.add(nextCreatureModifier);
        } else {
            next = nextCreatureModifier;
        }
    }
    //Przejście całego łańcucha odpowiedzialności
    public void handle() {
        if(next != null) {
            next.handle();
        }
    }
}

class DoubleAttackModifier extends CreatureModifier {
    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }
    @Override
    public void add(CreatureModifier nextCreatureModifier) {
        super.add(nextCreatureModifier);
    }
    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + " 's attack");
        creature.attack *= 2;
        super.handle();
    }
}

class IncreaseDefenseModifier extends CreatureModifier {
    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }
    @Override
    public void add(CreatureModifier nextCreatureModifier) {
        super.add(nextCreatureModifier);
    }
    @Override
    public void handle() {
        System.out.println("Increasing " + creature.name + " 's defense");
        creature.defense += 3;
        super.handle();
    }
}

class NoBonusesModifier extends CreatureModifier {
    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("No bonuses for you!");
    }
}

class Test {
    public static void main(String[] args) {
        Creature goblin = new Creature("Goblin", 2, 2);
        System.out.println(goblin);

        CreatureModifier root = new CreatureModifier(goblin);
        root.add(new DoubleAttackModifier(goblin));
        root.add(new IncreaseDefenseModifier(goblin));
        root.handle();//Rozpoczęcie działania łańcucha
        System.out.println(goblin);
    }
}
