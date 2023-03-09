package no.semignu.aoc2020;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Day11 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var grid = lines.stream().map(s -> s.chars().mapToObj(c -> (char)c).toArray(Character[]::new)).toArray(size -> new Character[size][size]);


        var changes = 1;

        while (changes > 0) {
            changes = 0;
            Character[][] newGrid = new Character[grid.length][grid[0].length];
            for (int y = 0; y < grid.length; y++){
                for (int x = 0; x < grid[0].length; x++) {
                    if (grid[y][x] == 'L' && countNeighbors(x,y,grid,c -> c == '#') == 0) {
                        newGrid[y][x] = '#';
                        changes++;
                    }
                    else if (grid[y][x] == '#' && countNeighbors(x,y,grid,c -> c == '#') >= 4) {
                        newGrid[y][x] = 'L';
                        changes++;
                    }
                    else {
                        newGrid[y][x] = grid[y][x];
                    }
                }
            }
            grid = newGrid;
        }

        var result = (int) Arrays.stream(grid).mapToLong(cs -> Arrays.stream(cs).filter(c -> c == '#').count()).sum();
        return String.format("%d", result);
    }


    private <T> long countNeighbors(int x, int y, T[][] grid, Predicate<T> predicate) {
        var yOffset = y == 0 ? 1 : 0;
        var xOffset = x == 0 ? 1 : 0;
        var neighborsAndSelf = Arrays.stream(grid).skip(y - 1 + yOffset).limit(3 - yOffset)
                .flatMap(row -> Arrays.stream(row).skip(x - 1 + xOffset).limit(3 - xOffset))
                .filter(predicate)
                .count();
        var self = predicate.test(grid[y][x]) ? 1 : 0;
        return neighborsAndSelf - self;
    }

    private <T> long countRays(int x, int y, T[][] grid, Predicate<T> predicate, Predicate<T> haltPredicate) {
        var vels = new int[]{-1,0,1};
        var sum = Arrays.stream(vels).flatMap(xVel -> Arrays.stream(vels).map(yVel -> checkRay(x + xVel, y + yVel, xVel, yVel, grid, predicate, haltPredicate) ? 1 : 0)).sum();
        return sum;
    }

    private <T> boolean checkRay(int x, int y, int xVel, int yVel, T[][] grid, Predicate<T> predicate, Predicate<T> haltPredicate) {
        if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length || (xVel == 0 && yVel == 0) || haltPredicate.test(grid[y][x])) {
            return false;
        }
        var ray =  predicate.test(grid[y][x]) || checkRay(x + xVel, y + yVel, xVel, yVel, grid, predicate, haltPredicate);
        return ray;
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var grid = lines.stream().map(s -> s.chars().mapToObj(c -> (char)c).toArray(Character[]::new)).toArray(size -> new Character[size][size]);

        var changes = 1;
        while (changes > 0) {
            changes = 0;
            Character[][] newGrid = new Character[grid.length][grid[0].length];
            for (int y = 0; y < grid.length; y++){
                for (int x = 0; x < grid[0].length; x++) {
                    if (grid[y][x] == 'L' && countRays(x,y,grid,c -> c == '#',c -> c == 'L') == 0) {
                        newGrid[y][x] = '#';
                        changes++;
                    }
                    else if (grid[y][x] == '#' && countRays(x,y,grid,c -> c == '#',c -> c == 'L') >= 5) {
                        newGrid[y][x] = 'L';
                        changes++;
                    }
                    else {
                        newGrid[y][x] = grid[y][x];
                    }
                }
            }
            grid = newGrid;
        }

        var result = (int) Arrays.stream(grid).mapToLong(cs -> Arrays.stream(cs).filter(c -> c == '#').count()).sum();
        return String.format("%d", result);
    }
}
