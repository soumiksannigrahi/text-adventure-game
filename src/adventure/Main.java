package adventure;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Room> rooms = ReadIn.loadRooms("data/Rooms.txt");

        for (Room r : rooms.values()) {
            r.linkRooms(rooms);
        }

        List<Item> items = ReadIn.loadItems("data/Items.txt");

        // Place items in rooms
        for (Item i : items) {
            Room r = rooms.get(i.getLocation());
            if (r != null) {
                r.addItem(i);
            }
        }

        Score score = new Score(ReadIn.loadScoreRules("data/Score.txt"));

        Player player = new Player(rooms.get("Lobby"));

        try (Scanner sc = new Scanner(System.in)) {

            System.out.println("Welcome to the Adventure Game!");

        while (true) {

            System.out.println(player.getCurrentRoom().getDetails());
            System.out.print("> ");

            String input = sc.nextLine().trim().toLowerCase();

            if (input.startsWith("go ")) {
                String dir = input.substring(3);
                player.move(player.getCurrentRoom().getExit(dir));
                score.check("VISIT", "-", player.getCurrentRoom().getName());
            }

            else if (input.equals("look")) {
                System.out.println(player.getCurrentRoom().getDetails());
            }

            else if (input.startsWith("take ")) {
                String itemName = input.substring(5);

                Item item = player.getCurrentRoom().removeItem(itemName);

                if (item != null) {
                    player.addItem(item);
                    item.setLocation("inventory");
                    System.out.println("Picked up " + itemName);

                    score.check("TAKE", itemName, player.getCurrentRoom().getName());
                } else {
                    System.out.println("Item not found.");
                }
            }

            else if (input.startsWith("drop ")) {
                String itemName = input.substring(5);

                Item item = player.dropItem(itemName);

                if (item != null) {
                    player.getCurrentRoom().addItem(item);
                    item.setLocation(player.getCurrentRoom().getName());
                    System.out.println("Dropped " + itemName);

                    score.check("DROP", itemName, player.getCurrentRoom().getName());
                }
            }

            else if (input.equals("inventory")) {
                player.showInventory();
            }

            else if (input.equals("score")) {
                // just trigger display
                score.check("NONE", "-", "-");
            }

            else if (input.equals("exit")) {
                System.out.println("Game Over");
                break;
            }

            else {
                System.out.println("Unknown command");
            }
        }
        }
    }
}