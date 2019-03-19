package core;

import java.util.*;

public class BitTexDatabase {

    private LinkedList<BibTeXEntity> elements = new LinkedList<>();
    private Map<String, String> strings = new HashMap<>();

    private static final Map<String, String> months = new HashMap<>() {
        {
            put("jan", "January");
            put("feb", "February");
            put("mar", "March");
            put("apr", "April");
            put("may", "May");
            put("jun", "June");
            put("jul", "July");
            put("aug", "August");
            put("sep", "September");
            put("oct", "October");
            put("dec", "December");

        }
    };

    public void put(BibTeXEntity bibTeXEntity) {
        elements.add(bibTeXEntity);
    }

    public void putString(String key, String value) {
        strings.put(key, value);
    }

    public String getValuString(String key) {
        return strings.get(key);
    }

    public String getMonth(String key) {
        return months.get(key.trim());
    }

    public List<BibTeXEntity> getElements() {
        return Collections.unmodifiableList(elements);
    }
}