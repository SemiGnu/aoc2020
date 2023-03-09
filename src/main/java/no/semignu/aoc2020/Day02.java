package no.semignu.aoc2020;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day02 extends Day
{

    @Override
    public String day1(Boolean test) {
        var lines = getData(test);
        var validPasswords = lines.stream().map(Instruction::fromString).filter(Instruction::isValid).count();

        return String.format("%d", validPasswords);
    }

    @Override
    public String day2(Boolean test) {
        var lines = getData(test);
        var validPasswords = lines.stream().map(Instruction::fromString).filter(Instruction::isValid2).count();

        return String.format("%d", validPasswords);
    }
}

class Instruction {
    private static Pattern pattern = Pattern.compile("(\\d*)-(\\d*) (\\w): (.*)");

    private int min;
    private int max;
    private char c;
    private String password;

    public Instruction(String line) {
        Matcher m = pattern.matcher(line);
        m.find();
        min = Integer.parseInt(m.group(1));
        max = Integer.parseInt(m.group(2));
        c = m.group(3).charAt(0);
        password = m.group(4);
    }

    public static Instruction fromString(String s) {
        return new Instruction(s);
    }

    public Boolean isValid() {
        var count = password.chars().filter(ch -> ch == c).count();
        return count <= max && count >= min;
    }
    public Boolean isValid2() {
        return password.charAt(min-1) == c ^ password.charAt(max-1) == c;
    }


}