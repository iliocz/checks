package ilio.checks;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChecksApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChecksApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("abc");
    }
}

