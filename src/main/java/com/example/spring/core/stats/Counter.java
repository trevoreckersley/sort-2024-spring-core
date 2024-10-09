package com.example.spring.core.stats;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Profile("managed")
public class Counter {

  private final AtomicInteger atomicInteger = new AtomicInteger();

  public int currentCount() {
    return atomicInteger.get();
  }

  public void increment() {
    atomicInteger.incrementAndGet();
  }

}
