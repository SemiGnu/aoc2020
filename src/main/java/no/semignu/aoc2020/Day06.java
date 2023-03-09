package no.semignu.aoc2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day06 extends Day
{
    private static Set<Integer> init = "abcdefghijklmnopqrstuwvxyz".chars().boxed().collect(Collectors.toSet());
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var groups = getGroups(lines);
        var result = groups.stream().map(s -> (int) s.chars().distinct().count()).mapToInt(Integer::intValue).sum();

        return String.format("%d", result);
    }

    private ArrayList<String> getGroups(ArrayList<String> lines) {
        ArrayList<String> groups = new ArrayList<>();
        var temp = "";
        for (var line : lines) {
            if (line.equals("")) {
                groups.add(temp);
                temp = "";
            }
            else {
                temp += line;
            }
        }
        groups.add(temp);
        return groups;
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var groups = getGroups2(lines);

        var result = groups.stream().map(passengers -> {
            var total = new HashSet<Integer>(init);
            for (var passenger : passengers) {
                total.retainAll(passenger);
            }
            return total.size();
        }).mapToInt(Integer::intValue).sum();

        return String.format("%d", result);
    }

    private ArrayList<ArrayList<Set<Integer>>> getGroups2(ArrayList<String> lines) {
        ArrayList<ArrayList<Set<Integer>>> groups = new ArrayList<>();
        ArrayList<Set<Integer>> temp = new ArrayList<>();
        for (var line : lines) {
            if (line.equals("")) {
                groups.add(temp);
                temp = new ArrayList<>();
            }
            else {
                temp.add(line.chars().boxed().collect(Collectors.toSet()));
            }
        }
        groups.add(temp);
        return groups;
    }
}
