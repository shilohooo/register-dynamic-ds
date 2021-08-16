package org.shiloh;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author shiloh
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(WebApplicationType.NONE)
                .run(args);
    }
}
