package de.fabiankrueger.warehouse;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Slf4j
@SpringBootApplication
public class WarehouseApplication {

    private static Logger log = LoggerFactory.getLogger(WarehouseApplication.class);

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WarehouseApplication.class);
//        long i = 0;
//        while(true) {
//            i++;
//            log.info("Logging to stdout: " + i);
//            Thread.sleep(5000);
//        }
    }
}
