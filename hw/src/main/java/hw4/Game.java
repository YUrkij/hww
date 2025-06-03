package hw4;
import java.util.ArrayList;
import java.util.List;




// Основной класс игры
public class Game {
    public static void main(String[] args) {
        Weapon sword = new Sword();
        Weapon bow = new Bow();

        Character warrior = new Warrior(sword);
        warrior.attack();

        warrior.changeWeapon(bow);
        warrior.attack();

        Item healthPotion = new Item("Зелье здоровья");
        Item manaPotion = new Item("Зелье маны");
        Item key = new Item("Ключ от сундука");

        InventoryBag smallBag = new InventoryBag("Маленькая сумка");
        smallBag.add(healthPotion);
        smallBag.add(manaPotion);

        InventoryBag mainBag = new InventoryBag("Рюкзак героя");
        mainBag.add(smallBag);
        mainBag.add(key);
        mainBag.add(new Item("Карта местности"));

        mainBag.add(new InventoryComponent() {
            @Override
            public void display() {
                System.out.println("- Оружие героя");
            }
        });

        System.out.println("\nИнвентарь героя:");
        mainBag.display();
    }
}

//Абстракция моста
abstract class Character {
    protected Weapon weapon;

    public Character(Weapon weapon) {
        this.weapon = weapon;
    }

    public void changeWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public abstract void attack();
}

class Warrior extends Character {
    public Warrior(Weapon weapon) {
        super(weapon);
    }

    @Override
    public void attack() {
        System.out.print("Воин атакует: ");
        weapon.use();
    }
}


//Реализация моста
interface Weapon {
    void use();
}

class Sword implements Weapon {
    @Override
    public void use() {
        System.out.println("Удар мечом!");
    }
}

class Bow implements Weapon {
    @Override
    public void use() {
        System.out.println("Выстрел из лука!");
    }
}


//Инвентарь (Компановщик)
interface InventoryComponent {
    void display();
}

class Item implements InventoryComponent {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("- " + name);
    }
}

class InventoryBag implements InventoryComponent {
    private final String name;
    private final List<InventoryComponent> items = new ArrayList<>();

    public InventoryBag(String name) {
        this.name = name;
    }

    public void add(InventoryComponent item) {
        items.add(item);
    }

    @Override
    public void display() {
        System.out.println("Сумка: " + name);
        for (InventoryComponent item : items) {
            item.display();
        }
    }
}