package org.wenzhuo4657.webhook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.util.pattern.PathPattern;
import org.wenzhuo4657.webhook.enmu.Constant;

@SpringBootApplication
public class Main implements ApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @Value("${token}")
    private  String token;

    @Value("${secret}")
    private String secret;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Constant.secret=secret;
        Constant.token=token;
    }
}