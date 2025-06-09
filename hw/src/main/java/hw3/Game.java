package hw3;

import java.util.Random;

/**
 * Main class игры
 */
public class Game {
    public static void main(String[] args) {
        Location[] locations = {
                new City(),
                new Forest(),
                new Mountains()
        };

        System.out.println("Герой начинает исследование:");
        for (Location location : locations) {
            System.out.println("\n--- " + location.getClass().getSimpleName() + " ---");
            location.search();
        }

        System.out.println("\nДополнительное исследование леса:");
        new Forest().search();
        new Forest().search();

        System.out.println("\nДополнительное исследование гор:");
        new Mountains().search();
        new Mountains().search();
    }
}
//Продукт 1
interface Monster {
    void interact();
}
//Реализация 1
class Goblin implements Monster {
    private final String insult;

    public Goblin(String insult) {
        this.insult = insult;
    }

    @Override
    public void interact() {
        System.out.println("Гоблин смеётся: \"Ты " + insult + "!\"");
    }
}
//Реализация 2
class Troll implements Monster {
    private final String roar;

    public Troll(String roar) {
        this.roar = roar;
    }

    @Override
    public void interact() {
        System.out.println("Тролль рычит: \"" + roar + "\"");
    }
}
//Абстрактная фабрика
interface MonsterFactory {
    Goblin createGoblin();
    Troll createTroll();
}
//Семейство лесных монстров
class ForestMonsterFactory implements MonsterFactory {
    @Override
    public Goblin createGoblin() {
        return new Goblin("камешек");
    }

    @Override
    public Troll createTroll() {
        return new Troll("А-а-а-р!");
    }
}
//Семейство горных монстров
class MountainMonsterFactory implements MonsterFactory {
    @Override
    public Goblin createGoblin() {
        return new Goblin("веточка");
    }

    @Override
    public Troll createTroll() {
        return new Troll("Борк!");
    }
}
//Абстрактный класс локации и его реализации
abstract class Location {
    public void search() {
        Monster monster = createMonster();
        if (monster != null) {
            System.out.print("Найден монстр! ");
            monster.interact();
        } else {
            System.out.println("Ничего не найдено...");
        }
    }

    protected abstract Monster createMonster();
}

class City extends Location {
    @Override
    protected Monster createMonster() {
        return null;
    }
}

class Forest extends Location {
    private final MonsterFactory factory = new ForestMonsterFactory();
    private final Random random = new Random();

    @Override
    protected Monster createMonster() {
        return random.nextBoolean()
                ? factory.createGoblin()
                : factory.createTroll();
    }
}

class Mountains extends Location {
    private final MonsterFactory factory = new MountainMonsterFactory();
    private final Random random = new Random();

    @Override
    protected Monster createMonster() {
        return random.nextBoolean()
                ? factory.createGoblin()
                : factory.createTroll();
    }
}

