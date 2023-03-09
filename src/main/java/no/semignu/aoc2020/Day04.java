package no.semignu.aoc2020;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Day04 extends Day
{
    private static Pattern pattern = Pattern.compile("((byr|iyr|eyr|hgt|hcl|ecl|pid):.+){7}");
    private static Pattern byrPattern = Pattern.compile("byr:(\\d{4})\\s");
    private static Pattern iyrPattern = Pattern.compile("iyr:(\\d{4})\\s");
    private static Pattern eyrPattern = Pattern.compile("eyr:(\\d{4})\\s");
    private static Pattern hgtPattern = Pattern.compile("hgt:(\\d+)(in|cm)\\s");
    private static Pattern hclPattern = Pattern.compile("hcl:#[\\da-f]{6}\\s");
    private static Pattern eclPattern = Pattern.compile("ecl:(amb|blu|brn|gry|grn|hzl|oth)\\s");
    private static Pattern pidPattern = Pattern.compile("pid:\\d{9}\\s");
    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var passports = getPassports(lines);
        var result = passports.stream().filter(this::isValid).count();

        return String.format("%d", result);
    }

    private boolean isValid(String passport) {
        return pattern.matcher(passport).find();
    }

    private ArrayList<String> getPassports(ArrayList<String> lines) {
        ArrayList<String> passports = new ArrayList<>();
        var temp = "";
        for (var line : lines) {
            if (line.equals("")) {
                passports.add(temp);
                temp = "";
            }
            else {
                temp += line + " ";
            }
        }
        passports.add(temp);
        return passports;
    }


    private boolean isValid2(String passport) {
        if (! pattern.matcher(passport).find()) return false;

        var byrMatcher = byrPattern.matcher(passport);
        if (! byrMatcher.find()) return false;
        var byr = Integer.parseInt(byrMatcher.group(1));
        if (byr < 1920 || byr > 2002) return false;

        var iyrMatcher = iyrPattern.matcher(passport);
        if (! iyrMatcher.find()) return false;
        var iyr = Integer.parseInt(iyrMatcher.group(1));
        if (iyr < 2010 || iyr > 2020) return false;

        var eyrMatcher = eyrPattern.matcher(passport);
        if (! eyrMatcher.find()) return false;
        var eyr = Integer.parseInt(eyrMatcher.group(1));
        if (eyr < 2020 || eyr > 2030) return false;

        var hgtMatcher = hgtPattern.matcher(passport);
        if (! hgtMatcher.find()) return false;
        var hgt = Integer.parseInt(hgtMatcher.group(1));
        var unt =hgtMatcher.group(2);
        if (unt.equals("cm") && (hgt < 150 || hgt > 193)) return false;
        if (unt.equals("in") && (hgt < 59 || hgt > 76)) return false;

        var hclMatcher = hclPattern.matcher(passport);
        if (! hclMatcher.find()) return false;

        var eclMatcher = eclPattern.matcher(passport);
        if (! eclMatcher.find()) return false;

        var pidMatcher = pidPattern.matcher(passport);
        if (! pidMatcher.find()) return false;

        return true;
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var passports = getPassports(lines);
        var result = passports.stream().filter(this::isValid2).count();

        return String.format("%d", result);
    }
}
