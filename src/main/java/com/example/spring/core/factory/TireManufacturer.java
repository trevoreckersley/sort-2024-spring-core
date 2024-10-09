package com.example.spring.core.factory;

import com.example.spring.core.item.Tire;

public class TireManufacturer implements ItemFactory {
  @Override
  public Tire createItem() {
    return new Tire();
  }
}
