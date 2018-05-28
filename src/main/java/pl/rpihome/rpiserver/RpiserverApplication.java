package pl.rpihome.rpiserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class RpiserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpiserverApplication.class, args);
//        try {
//            Process runtime = Runtime.getRuntime().exec("motion -n");
//            runtime.waitFor();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
