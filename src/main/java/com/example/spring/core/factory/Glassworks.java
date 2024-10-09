package com.example.spring.core.factory;

import com.example.spring.core.item.Item;
import com.example.spring.core.item.StainedGlass;
import com.example.spring.core.item.Window;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("windowFactory")
public class Glassworks implements ItemFactory {
  private boolean stained;

  @Override
  public Item createItem() {
    return stained ? new StainedGlass() : new Window();
  }

  @Autowired(required = false)
  public void setStained(boolean stained) {
    this.stained = stained;
  }
}
