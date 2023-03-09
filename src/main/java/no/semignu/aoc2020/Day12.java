package no.semignu.aoc2020;

public class Day12 extends Day
{
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);

        var north = 0;
        var east = 0;
        var steering = 0;
        var steerings = new int[][] {new int[]{0,1},new int[]{-1,0},new int[]{0,-1},new int[]{1,0}};

        for (var line : lines) {
            var magnitude = Integer.parseInt(line.substring(1));
            switch (line.charAt(0)) {
                case 'N':
                    north += magnitude;
                    break;
                case 'E':
                    east += magnitude;
                    break;
                case 'S':
                    north -= magnitude;
                    break;
                case 'W':
                    east -= magnitude;
                    break;
                case 'L':
                    steering += 4 - (magnitude / 90);
                    steering %= 4;
                    break;
                case 'R':
                    steering += (magnitude / 90);
                    steering %= 4;
                    break;
                case 'F':
                    north += magnitude * steerings[steering][0];
                    east += magnitude * steerings[steering][1];
                    break;
            }
        }


        var result = Math.abs(north) + Math.abs(east);

        return String.format("%d", result);
    }


    @Override
    public String day2(Boolean test) {
        var lines = getData(test);

        var north = 0;
        var east = 0;
        var waypoint = new int[] {1,10};
        var lRot = new int[]{-1,1};
        var rRot = new int[]{1,-1};

        for (var line : lines) {
            var magnitude = Integer.parseInt(line.substring(1));
            int[] rotation;
            switch (line.charAt(0)) {
                case 'N':
                    waypoint[0] += magnitude;
                    break;
                case 'E':
                    waypoint[1] += magnitude;
                    break;
                case 'S':
                    waypoint[0] -= magnitude;
                    break;
                case 'W':
                    waypoint[1] -= magnitude;
                    break;
                case 'L':
                    for (var i = 0; i < magnitude / 90; i++) {
                        waypoint = new int[]{lRot[1] * waypoint[1], lRot[0] * waypoint[0]};
                    }
                    break;
                case 'R':
                    for (var i = 0; i < magnitude / 90; i++) {
                        waypoint = new int[]{rRot[1] * waypoint[1], rRot[0] * waypoint[0]};
                    }
                    break;
                case 'F':
                    north += magnitude * waypoint[0];
                    east += magnitude * waypoint[1];
                    break;
            }
        }


        var result = Math.abs(north) + Math.abs(east);

        return String.format("%d", result);
    }
}
