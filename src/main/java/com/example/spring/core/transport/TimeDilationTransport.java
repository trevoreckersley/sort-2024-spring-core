package com.example.spring.core.transport;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

import com.example.spring.core.item.Item;
import org.springframework.stereotype.Component;

@Component
public class TimeDilationTransport implements Carrier {
  @Override
  public void handle(Item item) {
    System.out.println("Shipping item via flux capacitated capsule: " + item);
    Duration duration = Duration.ofMillis(ThreadLocalRandom.current().nextLong(Duration.ofDays(100).toMillis()));
    System.out.printf(
      "ETA: %s days, %s hours, %s minutes, %s seconds, %s milliseconds\n",
      duration.toDays(),
      duration.toHoursPart(),
      duration.toMinutesPart(),
      duration.toSecondsPart(),
      duration.toMillisPart());
  }
}
