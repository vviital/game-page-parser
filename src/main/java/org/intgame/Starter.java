package org.intgame;

import org.intgame.finder.GamesLoader;
import org.intgame.services.interfaces.LoadGameService;
import org.intgame.tester.Tester;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * Created by vviital on 5/12/16.
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "org.intgame")
@EnableJpaRepositories(basePackages = {"org.intgame"})
@EntityScan(basePackages = "org.intgame.model")
public class Starter {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Starter.class, args);
        Tester test = context.getBean(Tester.class);
        test.test();
        //(new GamesLoader()).load();
    }

    @Bean
    public Tester getBean() {
        return new Tester();
    }
}
