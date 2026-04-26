package adventure;

public class Item {
    private String name;
    private String location;
    private String description;

    public Item(String name, String location, String description) {
        this.name = name.trim();
        this.location = location.trim();
        this.description = description.trim();
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }

    public void setLocation(String location) {
        this.location = location;
    }
}
