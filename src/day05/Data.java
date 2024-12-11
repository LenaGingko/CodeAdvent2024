package day05;

import java.util.List;
import java.util.Map;

public class Data {
    Map<Integer, List<Integer>> rules;
    List<Integer[]> updates;

    Data(Map<Integer, List<Integer>> rules, List<Integer[]> updates) {
        this.rules = rules;
        this.updates = updates;
    }
}
