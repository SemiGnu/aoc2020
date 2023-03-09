package no.semignu.aoc2020;

import lombok.extern.log4j.Log4j2;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;

@Log4j2
public abstract class Day {
    public  String day1() {
        return day1(false);
    }

    public abstract String day1(Boolean test);

    public  String day2() {
        return day2(false);
    }
    public abstract String day2(Boolean test);

    protected ArrayList<String> getData(Boolean test) {
        var day = this.getClass().getSimpleName();
        ArrayList<String> lines = new ArrayList<>();
        var filename = "classpath:" + day + "/" + (test ? "test" : "data") + ".txt";
        try {
            File file = ResourceUtils.getFile(filename);
            InputStream in = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (br.ready()) {
                lines.add(br.readLine());
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return lines;
    }
}
