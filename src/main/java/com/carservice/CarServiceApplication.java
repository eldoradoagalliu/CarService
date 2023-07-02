package com.carservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;

@SpringBootApplication
public class CarServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CarServiceApplication.class).headless(false).run(args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void openBrowserAfterStartup() throws IOException {
        java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://localhost:8000"));
    }
}
