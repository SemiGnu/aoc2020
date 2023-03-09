package no.semignu.aoc2020;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var adapters = lines.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        var joltages = new int[] {1,0,1};

        for (var i = 1; i < adapters.size(); i++) {
            joltages[adapters.get(i) - adapters.get(i-1) - 1]++;
        }

        var result = joltages[0] * joltages[2];

        return String.format("%d", result);
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var adapters = lines.stream().map(Integer::parseInt).sorted().collect(Collectors.toList());
        adapters.add(0,0);

        var consecutives = new ArrayList<Boolean>();

        for (var i = 0; i < adapters.size() - 1; i++) {
            consecutives.add(adapters.get(i+1) == adapters.get(i) + 1);
        }

        var hist = new int[5];
        var counter = 0;
        for (var i = 0; i < consecutives.size(); i++) {
            if(consecutives.get(i)) {
                counter++;
            }
            else {
                hist[counter]++;
                counter = 0;
            }
        }
        hist[counter]++;

        var result = Math.pow(2, hist[2]) * Math.pow(4, hist[3]) * Math.pow(7, hist[4]);
        return String.format("%.0f", result);
    }
}

/*
0 x
1
4 x
5 x
6 x
7
10 x
11 x
12
15 x
16
19
22
 */