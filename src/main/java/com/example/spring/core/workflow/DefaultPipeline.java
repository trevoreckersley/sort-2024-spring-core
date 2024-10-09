package com.example.spring.core.workflow;

import com.example.spring.core.factory.ItemFactory;
import com.example.spring.core.stats.Counter;
import com.example.spring.core.transport.Carrier;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.lang.NonNull;


public class DefaultPipeline implements Pipeline, BeanNameAware {

  private final ItemFactory factory;
  private final Carrier carrier;
  private final Counter counter;
  private String name;

  public DefaultPipeline(ItemFactory factory, Carrier carrier, Counter counter) {
    this.factory = factory;
    this.carrier = carrier;
    this.counter = counter;
    this.name = this.toString();
  }

  @Override
  public void execute() {
    counter.increment();
    System.out.printf("Executing default pipeline %s. This is call #%s!\n", name, counter.currentCount());
    carrier.handle(factory.createItem());
  }

  @Override
  public void setBeanName(@NonNull String name) {
    this.name = name;
  }
}
