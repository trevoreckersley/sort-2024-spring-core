package com.example.spring.core.transport;

import com.example.spring.core.item.Item;
import org.springframework.stereotype.Component;

@Component
public class Immediate implements Carrier {

  @Override
  public void handle(Item item) {
    System.out.println("Delivering item directly: " + item);
  }
}
