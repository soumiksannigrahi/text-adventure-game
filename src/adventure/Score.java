package adventure;

import java.util.*;

public class Score {

    private int total = 0;
    private List<String[]> rules;

    public Score(List<String[]> rules) {
        this.rules = rules;
    }

    public void check(String action, String item, String location) {
        for (String[] r : rules) {
            String rAction = r[0].trim();
            String rItem = r[1].trim();
            String rLocation = r[2].trim();
            int points = Integer.parseInt(r[3].trim());

            if (rAction.equalsIgnoreCase(action)) {

                boolean itemMatch = rItem.equals("-") || rItem.equalsIgnoreCase(item);
                boolean locMatch = rLocation.equalsIgnoreCase(location);

                if (itemMatch && locMatch) {
                    total += points;
                    System.out.println("⭐ +" + points + " points! Total: " + total);
                }
            }
        }
    }
}