package no.semignu.aoc2020;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Day14 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var memory = new HashMap<Long, Long>();
        String mask = "";
        for (var line : lines) {
            var instructions = line.split(" = ");
            if (instructions[0].equals("mask")) {
                mask = instructions[1];
            } else {
                var address = Long.parseLong(instructions[0].substring(4, instructions[0].length() - 1));
                var value = Long.parseLong(instructions[1]);
                value =  applyMask(value, mask);
                memory.put(address, value);
            }
        }
        var result = memory.values().stream().mapToLong(Long::longValue).sum();

        return String.format("%d", result);
    }

    private long applyMask(long value, String mask) {
        value |= Long.parseLong(mask.replaceAll("X", "0"), 2);
        value &= Long.parseLong(mask.replaceAll("X", "1"), 2);
        return value;
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var memory = new HashMap<Long, Long>();
        String mask = "";
        for (var line : lines) {
            var instructions = line.split(" = ");
            if (instructions[0].equals("mask")) {
                mask = instructions[1];
            } else {
                var address = Long.parseLong(instructions[0].substring(4, instructions[0].length() - 1));
                var addresses = getAddresses(address, mask);
                var value = Long.parseLong(instructions[1]);
                addresses.forEach(a -> memory.put(a, value));
            }
        }
        var result = memory.values().stream().mapToLong(Long::longValue).sum();

        return String.format("%d", result);
    }

    private Set<Long> getAddresses(long address, String mask) {
        address |= Long.parseLong(mask.replaceAll("X", "0"), 2);
        var addresses = new HashSet<Long>();
        addresses.add(address);
        for (int i = 0; i < 36; i++) {
            if (mask.charAt(i) == 'X') {
                var setMask = 1L << (35 - i);
                var newAddresses = new HashSet<Long>();
                addresses.forEach(a -> {
                    newAddresses.add(a | setMask);
                    newAddresses.add(a & ~setMask);
                });
                addresses.addAll(newAddresses);
            }
        }
        return addresses;
    }
}
