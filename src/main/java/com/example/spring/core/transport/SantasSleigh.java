package com.example.spring.core.transport;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

import com.example.spring.core.item.Item;
import org.springframework.stereotype.Component;

@Component
public class SantasSleigh implements Carrier {
  @Override
  public void handle(Item item) {
    System.out.println("Shipping item by Santa's sleigh:" + item);
    long daysToChristmas = Duration.between(
        LocalDateTime.now(),
        Year.now().atMonth(Month.DECEMBER).atDay(25).atStartOfDay())
      .toDays();
    System.out.printf("ETA: %s days\n", daysToChristmas);
  }
}
