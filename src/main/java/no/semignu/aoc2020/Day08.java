package no.semignu.aoc2020;

import java.util.ArrayList;
import java.util.HashSet;

public class Day08 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var result = accumulate(lines);
        return String.format("%d", result);
    }

    private int accumulate(ArrayList<String> instructions) {
        var accumulator = 0;
        var pointer = 0;
        var set = new HashSet<Integer>();
        while (!set.contains(pointer) && pointer < instructions.size()) {
            set.add(pointer);
            var split = instructions.get(pointer).split(" ");
            if (split[0].equals("nop")) {
                pointer++;
            } else if (split[0].equals("jmp")) {
                pointer += Integer.parseInt(split[1]);
            } else {
                pointer++;
                accumulator += Integer.parseInt(split[1]);
            }
        }
        return accumulator;
    }

    @Override
    public String day2(Boolean test) {
        var instructions = getData(test);
        var pointer = 0;
        var set = new HashSet<Integer>();
        var swapPointer = 0;
        while (swapPointer == 0) {
            set.add(pointer);
            var split = instructions.get(pointer).split(" ");
            if (split[0].equals("nop")) {
                pointer++;
            } else if (split[0].equals("jmp")) {
                pointer += Integer.parseInt(split[1]);
            } else {
                pointer++;
            }
            var swapped = new ArrayList<>(instructions);
            swapped.set(pointer, swap(swapped.get(pointer)));
            var swapSet = new HashSet<>(set);
            if (willTerminate(pointer, swapped, swapSet)) {
                swapPointer = pointer;
            }
        }
        instructions.set(swapPointer, swap(instructions.get(swapPointer)));

        var result = accumulate(instructions);
        return String.format("%d", result);
    }

    private String swap(String instruction) {
        if (instruction.contains("nop")) {
            return instruction.replace("nop", "jmp");
        }
        return instruction.replace("jmp", "nop");
    }

    private boolean willTerminate(int pointer, ArrayList<String> instructions, HashSet<Integer> set) {
        boolean terminated = false;
        while (!set.contains(pointer) && !terminated) {
            if (pointer == instructions.size() - 1 ){
                terminated = true;
            }
            set.add(pointer);
            var split = instructions.get(pointer).split(" ");
            if (split[0].equals("jmp")) {
                pointer += Integer.parseInt(split[1]);
            } else {
                pointer++;
            }
        }
        return terminated;
    }

}
