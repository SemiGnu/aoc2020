package no.semignu.aoc2020;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class Day07 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var bags = lines.stream().map(Bag::fromString).collect(Collectors.toMap(key -> key.getName(), value -> value));
        var result = bags.keySet().stream().filter(name -> canContain(name, "shiny gold", bags)).count();
        return String.format("%d", result);
    }

    private boolean canContain(String parent, String name, Map<String, Bag> bags) {
        var childBags = bags.get(parent).getChildBags();
        if (childBags.containsKey(name)) {
            return true;
        }
        if (childBags.isEmpty()) {
            return false;
        }
        return childBags.keySet().stream().anyMatch(child -> canContain(child, name, bags));
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var bags = lines.stream().map(Bag::fromString).collect(Collectors.toMap(key -> key.getName(), value -> value));
        var result = totalBags("shiny gold", bags);
        return String.format("%d", result);
    }

    private int totalBags(String parent, Map<String, Bag> bags) {
        var childBags = bags.get(parent).getChildBags();
        if (childBags.isEmpty()) {
            return 0;
        }
        return childBags.keySet().stream().mapToInt(s -> childBags.get(s) * (1 + totalBags(s, bags))).sum();
    }

}

@Getter
@AllArgsConstructor
class Bag{
    private String name;
    private Map<String, Integer> childBags;

    public static Bag fromString(String line) {
        var nameSplit = line.split(" bags contain ");
        var name = nameSplit[0];
        var childSplit = nameSplit[1].split(", ");
        var childBags = childSplit[0].equals("no other bags.")
            ? new HashMap<String, Integer>()
            : Arrays.stream(childSplit).map(c -> c.split(" ")).collect(Collectors.toMap(key -> key[1] + " " + key[2], value -> Integer.parseInt(value[0])));

        return new Bag(name, childBags);
    }

}