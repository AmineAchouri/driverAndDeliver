package fr.carrefour.kata;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.serializer.JsonSerde;

@EnableBinding(value = {OrderBinder.class})
@SpringBootApplication
@Slf4j
public class MvpApplication {
  public final static String STATE_STORE_NAME = "scs-100-2-order-events";

  public static void main(String[] args) {
    SpringApplication.run(MvpApplication.class, args);
    log.info("The Mvp Application has started...");
  }

  @Bean
  public Serde<Order> orderJsonSerde() {
    return new JsonSerde<>(Order.class, new ObjectMapper());
  }
}
