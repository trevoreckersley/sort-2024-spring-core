package com.example.spring.core.workflow;

import java.util.concurrent.ThreadLocalRandom;

public class MisfiringPipeline implements Pipeline, AutoCloseable {

  Long initialDelay;

  @Override
  public void execute() {
    if (initialDelay != null) {
      try {
        Thread.sleep(initialDelay);
        System.out.println("...BOOM! Misfiring failed to do anything useful after " + initialDelay + "ms");
        if (initialDelay > 10000) {
          initialDelay = 0L;
        }
        initialDelay += ThreadLocalRandom.current().nextLong(1000);
      }
      catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
    }
  }

  public void init() {
    initialDelay = ThreadLocalRandom.current().nextLong(1000);
    System.out.println("Initializing misfiring pipeline with delay: " + initialDelay);
  }

  @Override
  public void close() {
    System.out.println("Closing misfiring pipeline");
    initialDelay = null;
  }
}
