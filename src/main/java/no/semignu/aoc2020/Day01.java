package no.semignu.aoc2020;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day01 extends Day
{

    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var nums = lines.stream().map(Integer::parseInt).collect(Collectors.toList());


        for(int i = 0; i < nums.size(); i++) {
            for(int j = i + 1; j < nums.size(); j++) {
                for(int k = j + 1; k < nums.size(); k ++) {
                    if (nums.get(i) + nums.get(j) + nums.get(k) == 2020) {
                        return String.format("%d", nums.get(i) * nums.get(j) * nums.get(k));
                    }
                }
            }
        }

        return "NOPE";
    }

    @Override
    public String day2(Boolean test) {
        return null;
    }
}
