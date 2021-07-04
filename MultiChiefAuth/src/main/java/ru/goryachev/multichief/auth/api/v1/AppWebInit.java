package ru.goryachev.multichief.auth.api.v1;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.goryachev.multichief.auth.config.SecurityConfig;

@SpringBootApplication
@ComponentScan(basePackages = "ru.goryachev.multichief")
@EnableJpaRepositories(basePackages = "ru.goryachev.multichief")
@EntityScan(basePackages = "ru.goryachev.multichief")
public class AppWebInit extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder appBuilder) {
        return appBuilder.sources(AppWebInit.class, SecurityConfig.class);
    }
}