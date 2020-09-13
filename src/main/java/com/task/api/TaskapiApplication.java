package com.task.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication
@EnableJpaRepositories
public class TaskapiApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TaskapiApplication.class, args);
    }

}
