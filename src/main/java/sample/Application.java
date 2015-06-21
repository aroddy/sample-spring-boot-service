package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by devyn on 3/9/15.
 */
@EnableAutoConfiguration(exclude = { })
@ComponentScan(value = { "sample.controller", "sample.service" })
@Configuration
public class Application {
    private static ConfigurableApplicationContext context;

    public static void main(final String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);
        context = app.run(args);
    }

    /**
     * Explicitly shutdown the application context. Spring guarantees that this explicit shutdown removes the shutdown hook and therefore prevents multiple
     * attempts at shutting down the same context.
     */
    public static void stop() {
        SpringApplication.exit(context);
    }
}