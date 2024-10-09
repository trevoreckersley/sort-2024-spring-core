
package com.example.spring.core.transport;

import com.example.spring.core.item.Item;
import org.springframework.stereotype.Component;

@Component
public class Post implements Carrier {
  @Override
  public void handle(Item item) {
    System.out.println("Shipping item by post: " + item);
    System.out.println("ETA: 7 days");
  }
}
