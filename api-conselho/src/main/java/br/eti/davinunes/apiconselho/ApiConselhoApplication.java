package br.eti.davinunes.apiconselho;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiConselhoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiConselhoApplication.class, args);
    }

    @RestController
    class HelloController {

        @GetMapping("/")
        public String hello() {
            return "Hello, World!";
        }
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
