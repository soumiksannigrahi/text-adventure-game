package adventure;

import java.util.*;

public class Room {

    private String name;
    private String description;

    private HashMap<String, Room> exits = new HashMap<>();
    private String[] exitNames;

    private List<Item> items = new ArrayList<>();

    public Room(String name, String description) {
        this.name = name.trim();
        this.description = description.trim();
    }

    public String getName() { return name; }

    public void setExitNames(String[] exits) {
        this.exitNames = exits;
    }

    public void linkRooms(HashMap<String, Room> allRooms) {
        String[] directions = {"north", "south", "east", "west"};

        for (int i = 0; i < exitNames.length; i++) {
            if (!exitNames[i].equals("-")) {
                exits.put(directions[i], allRooms.get(exitNames[i].trim()));
            }
        }
    }

    public Room getExit(String dir) {
        return exits.get(dir);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for (Item i : items) {
            if (i.getName().equalsIgnoreCase(name)) {
                items.remove(i);
                return i;
            }
        }
        return null;
    }

    public List<Item> getItems() {
        return items;
    }

    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n ").append(name).append("\n");
        sb.append(description).append("\n");

        sb.append("Exits: ").append(exits.keySet()).append("\n");

        if (items.isEmpty()) {
            sb.append("Items: none\n");
        } else {
            sb.append("Items: ");
            for (Item i : items) {
                sb.append(i.getName()).append(", ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}