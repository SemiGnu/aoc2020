package no.semignu.aoc2020;

import java.util.stream.Collectors;

public class Day05 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);

        var result = lines.stream().map(this::calculateSeatId).max(Integer::compare).get();

        return String.format("%d", result);
    }

    public int calculateSeatId(String seat) {
        seat = seat
                .replaceAll("B", "1")
                .replaceAll("F", "0")
                .replaceAll("R", "1")
                .replaceAll("L", "0");
        var row = Integer.parseInt(seat, 2) >> 3;
        var col = Integer.parseInt(seat, 2) & 7;
        return row * 8 + col;
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var orderedSeats = lines.stream().map(this::calculateSeatId).sorted().collect(Collectors.toList());
        var i = 0;
        while (orderedSeats.get(i) + 1 == orderedSeats.get(i + 1)) {
            i++;
        }
        var result = orderedSeats.get(i) + 1;
        return String.format("%d", result);
    }
}
