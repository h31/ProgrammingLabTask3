package GUI;

import java.util.*;

public class Desk {
    private Map<String, String> table;
    private List<String> nums;
    private List<String> keys = new ArrayList<>(Arrays.asList("a:1", "b:1", "c:1", "d:1", "a:2", "b:2", "c:2",
        "d:2", "a:3", "b:3", "c:3", "d:3", "a:4", "b:4", "c:4", "d:4"));

    public Desk() {
        nums = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "11", "12", "13", "14", "15", "16"));
        table = new HashMap<>();
        int rangeOfRandom = 16;
        int randomNum;

        for (String key : keys) {
            randomNum = (int) (Math.random() * rangeOfRandom);
            table.put(key, nums.get(randomNum));
            nums.remove(randomNum);
            rangeOfRandom--;
        }

    }

    public List<String> getKeys() {
        return keys;
    }

    public Map<String, String> getTable() {
        return table;
    }
}
