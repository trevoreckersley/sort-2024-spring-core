package com.example.spring.core.transport;

import com.example.spring.core.item.Item;

public interface Carrier {
  void handle(Item item);
}
