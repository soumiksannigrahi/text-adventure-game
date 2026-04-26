package adventure;

import java.io.*;
import java.util.*;

public class ReadIn {

    public static HashMap<String, Room> loadRooms(String path) {
        HashMap<String, Room> rooms = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String name = line;

                String exitsLine = br.readLine();
                String[] exits = exitsLine.split(",");

                StringBuilder desc = new StringBuilder();
                while (!(line = br.readLine()).equals("END")) {
                    desc.append(line).append("\n");
                }

                Room room = new Room(name, desc.toString());
                room.setExitNames(exits);

                rooms.put(name.trim(), room);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public static List<Item> loadItems(String path) {
        List<Item> items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                String name = line;
                String location = br.readLine();

                StringBuilder desc = new StringBuilder();
                while (!(line = br.readLine()).equals("END")) {
                    desc.append(line).append("\n");
                }

                items.add(new Item(name, location, desc.toString()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public static List<String[]> loadScoreRules(String path) {
        List<String[]> rules = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = br.readLine()) != null) {
                rules.add(line.split(","));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rules;
    }
}