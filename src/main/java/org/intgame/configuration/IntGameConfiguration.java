package org.intgame.configuration;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by vviital on 2.5.16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "org.intgame")
public class IntGameConfiguration {

}
