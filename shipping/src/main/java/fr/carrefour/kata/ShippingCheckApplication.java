package fr.carrefour.kata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShippingCheckApplication {

  public static final Logger log = LoggerFactory.getLogger(ShippingCheckApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(ShippingCheckApplication.class, args);
    log.info("The Shipping Application has started...");
  }

}
