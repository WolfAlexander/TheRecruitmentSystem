package configapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;


/**
 * Launches configuration
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigLauncher {
    public static void main(String[] args) {
        SpringApplication.run(ConfigLauncher.class, args);
    }
}