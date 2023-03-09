package no.semignu.aoc2020;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day03 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var trees = calculateSlope(lines, 3, 1);
        return String.format("%d", trees);
    }

    private int calculateSlope(ArrayList<String> lines, int right, int down) {
        var trees = 0;
        var xPos = 0;
        for (var i = 0; i < lines.size(); i += down) {
            var line = lines.get(i);
            var isTree = line.charAt(xPos % line.length()) == '#';
            xPos += right;
            trees += isTree ? 1 : 0;
        }
        return trees;
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        long trees = calculateSlope(lines, 1, 1);
        trees *= calculateSlope(lines, 3, 1);
        trees *= calculateSlope(lines, 5, 1);
        trees *= calculateSlope(lines, 7, 1);
        trees *= calculateSlope(lines, 1, 2);
        return String.format("%d", trees);
    }
}

