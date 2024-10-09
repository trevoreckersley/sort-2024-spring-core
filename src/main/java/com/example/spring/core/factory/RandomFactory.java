package com.example.spring.core.factory;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.example.spring.core.item.Item;
import org.springframework.stereotype.Component;

@Component
public class RandomFactory implements ItemFactory {

  private final List<ItemFactory> factories;

  public RandomFactory(List<ItemFactory> factories) {
    this.factories = factories;
  }

  @Override
  public Item createItem() {
    int index = ThreadLocalRandom.current().nextInt(0, factories.size());
    return factories.get(index).createItem();
  }

}
