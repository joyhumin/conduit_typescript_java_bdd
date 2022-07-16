package nz.co.joyhu.acceptance;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class AcceptanceConfiguration {

    @Bean
    public Random random() {
        return ThreadLocalRandom.current();
    }
}
