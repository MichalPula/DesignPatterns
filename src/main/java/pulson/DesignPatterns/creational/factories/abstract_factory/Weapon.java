package pulson.DesignPatterns.creational.factories.abstract_factory;


interface Weapon {
    void equip();
}
class DwarvenWeapon implements Weapon {
    @Override
    public void equip() {
        System.out.println("Equipping weapon for a dwarf");
    }
}
class ElvenWeapon implements Weapon {
    @Override
    public void equip() {
        System.out.println("Equipping weapon for a elf");
    }
}


interface Armor {
    void equip();
}
class DwarvenArmor implements Armor {
    @Override
    public void equip() {
        System.out.println("Equipping armor for a dwarf");
    }
}
class ElvenArmor implements Armor {
    @Override
    public void equip() {
        System.out.println("Equipping armor for a elf");
    }
}




interface MainForge {
    Weapon craftWeapon();
    Armor craftArmor();
}
class DwarvenForge implements MainForge {
    @Override
    public DwarvenWeapon craftWeapon() {
        System.out.println("Crafting dwarven weapon");
        return new DwarvenWeapon();
    }

    @Override
    public DwarvenArmor craftArmor() {
        System.out.println("Crafting dwarven armor");
        return new DwarvenArmor();
    }
}
class ElvenForge implements MainForge {
    @Override
    public ElvenWeapon craftWeapon() {
        System.out.println("Crafting elven weapon");
        return new ElvenWeapon();
    }

    @Override
    public ElvenArmor craftArmor() {
        System.out.println("Crafting elven armor");
        return new ElvenArmor();
    }
}

class Test {
    public static void main(String[] args) {
        MainForge dwarvenForge = new DwarvenForge();
        MainForge elvenForge = new ElvenForge();

        DwarvenWeapon dwarvenWeapon = (DwarvenWeapon) dwarvenForge.craftWeapon();
        DwarvenArmor dwarvenArmor = (DwarvenArmor) dwarvenForge.craftArmor();
        dwarvenWeapon.equip();
        dwarvenArmor.equip();
        //Crafting dwarven weapon
        //Crafting dwarven armor
        //Equipping weapon for a dwarf
        //Equipping armor for a dwarf
    }
}


