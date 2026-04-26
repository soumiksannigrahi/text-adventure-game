package adventure;

import java.util.*;

public class Player {

    private Room currentRoom;
    private List<Item> inventory = new ArrayList<>();

    public Player(Room start) {
        currentRoom = start;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void move(Room next) {
        if (next != null) {
            currentRoom = next;
        } else {
            System.out.println("You can't go that way.");
        }
    }

    public void addItem(Item item) {
        inventory.add(item);
    }

    public Item dropItem(String name) {
        for (Item i : inventory) {
            if (i.getName().equalsIgnoreCase(name)) {
                inventory.remove(i);
                return i;
            }
        }
        return null;
    }

    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Inventory:");
            for (Item i : inventory) {
                System.out.println("- " + i.getName());
            }
        }
    }
}
