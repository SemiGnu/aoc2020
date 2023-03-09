package no.semignu.aoc2020;

import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.util.ArrayList;
import java.util.Arrays;

public class Day13 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var time = Integer.parseInt(lines.get(0));
        var result = Arrays.stream(lines.get(1).split(","))
            .filter(s -> !s.equals("x"))
            .mapToInt(Integer::parseInt)
            .mapToObj(t -> new Pair<Integer,Integer>(t - (time % t), t * ( t- (time % t))))
            .sorted()
            .findFirst()
            .get().getValue1();
        return String.format("%d", result);
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var times = lines.get(1).split(",");
        var routes = new ArrayList<Pair<Long, Integer>>();
        for (var i = 0; i < times.length; i++) {
            if (!times[i].equals("x")) {
                routes.add(new Pair<>(Long.parseLong(times[i]), i));
            }
        }
        var startStep = new Pair<>(0L, routes.get(0).getValue0());
        for (int i = 1; i < routes.size(); i++) {
            startStep = getNextStartStep(startStep, routes.get(i).getValue0(), routes.get(i).getValue1());
        }
        var result = startStep.getValue0();
        return String.format("%d", result);
    }


    private Pair<Long, Long> getNextStartStep(Pair<Long, Long> startStep, long next, int offset) {
        var start = startStep.getValue0();
        while (start % next != next - (offset % next)) {
            start += startStep.getValue1();
        }
        return new Pair<>(start, startStep.getValue1() * next);
    }

}
