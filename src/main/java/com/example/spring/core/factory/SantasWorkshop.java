package com.example.spring.core.factory;

import com.example.spring.core.item.Toy;

public class SantasWorkshop implements ItemFactory {
  @Override
  public Toy createItem() {
    return new Toy();
  }
}
