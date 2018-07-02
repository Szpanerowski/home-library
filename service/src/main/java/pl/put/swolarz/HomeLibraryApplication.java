package pl.put.swolarz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({
        "pl.put.swolarz.application.service",
        "pl.put.swolarz.application.common.helper",
        "pl.put.swolarz.rest.controller"
})
@SpringBootApplication
public class HomeLibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeLibraryApplication.class, args);
    }
}
