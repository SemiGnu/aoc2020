package no.semignu.aoc2020;

import java.util.List;
import java.util.stream.Collectors;

public class Day09 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var numbers = lines.stream().map(Long::parseLong).collect(Collectors.toList());

        var preambleLength = System.getenv("TEST").equals("true") ? 5 : 25;
        var pointer = preambleLength;
        while (isValid(pointer, numbers, preambleLength)){
            pointer ++;
        }
        var result = numbers.get(pointer);
        return String.format("%d", result);
    }

    private boolean isValid(int pointer, List<Long> nums, int preambleLength) {
        for (var i = pointer - preambleLength; i < pointer; i++) {
            for (var j = i + 1; j < pointer; j++) {
                if (nums.get(i) + nums.get(j) == nums.get(pointer)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var numbers = lines.stream().map(Long::parseLong).collect(Collectors.toList());

        var goal =System.getenv("TEST").equals("true") ? 127L : 552655238L;
        var sum = 0L;
        var lowPointer = 0;
        var highPointer = 0;

        while ( sum != goal) {
            sum += numbers.get(highPointer++);
            while (sum > goal) {
                sum -= numbers.get(lowPointer++);
            }
        }

        var section = numbers.stream().skip(lowPointer).limit(highPointer - lowPointer).sorted(Long::compareTo).collect(Collectors.toList());
        var result = section.get(0) + section.get(section.size() -1);

        return String.format("%d", result);
    }
}
