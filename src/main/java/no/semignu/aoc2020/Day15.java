package no.semignu.aoc2020;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Day15 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var map = new HashMap<Integer, Integer>();
        var numbers = Arrays.stream(lines.get(0).split(",")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < numbers.length; i++) {
            map.put(numbers[i], i);
        }

        var firstTime = true;
        var lastNum = numbers[numbers.length - 1];
        for (int i = 3; i < 10 ; i++){
            var next = 0;
            if (!firstTime) {
                next = i - map.get(lastNum);
            }
            firstTime = !map.containsKey(next);
            map.put(next, i);
            lastNum = next;
        }


        var result = nextNum;

        return String.format("%d", result);
    }



    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var result = 0;

        return String.format("%d", result);
    }
}
