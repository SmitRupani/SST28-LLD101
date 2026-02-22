import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
    private final Map<String, MenuItem> items = new LinkedHashMap<>();

    public void addMenuItem(MenuItem item) { items.put(item.id, item); }
    public MenuItem get(String id) { return items.get(id); }
}