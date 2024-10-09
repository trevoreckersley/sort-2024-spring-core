package com.example.spring.core.factory;

import com.example.spring.core.item.Burger;

public class HamburgerShop implements ItemFactory {
  private final String shopName;
  private final String burgerName;

  public HamburgerShop(String shopName, String burgerName) {
    this.shopName = shopName;
    this.burgerName = burgerName;
  }

  @Override
  public Burger createItem() {
    System.out.println("Fresh made burger from " + shopName + " is ready!");
    return new Burger(burgerName);
  }
}
