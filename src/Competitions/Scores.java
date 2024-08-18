package Competitions;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Scores {
    private Map<String, Date> scores = Collections.synchronizedMap(new HashMap());

    public void add(String name, Date date) {
        if (name != null && scores.containsKey(name))
            scores.put(name, date);
    }

    public Map<String,Date> getAll() {
        return scores;
    }
}
