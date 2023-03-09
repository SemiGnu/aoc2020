package no.semignu.aoc2020;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
@Log4j2
@Component
public class Dayman {
    @PostConstruct
    public void Day() {
        log.info("IT ");
    }
}
