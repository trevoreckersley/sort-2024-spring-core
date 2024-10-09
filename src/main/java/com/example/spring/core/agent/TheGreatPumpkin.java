package com.example.spring.core.agent;

import java.util.List;

import com.example.spring.core.annotations.Viablity;
import com.example.spring.core.workflow.Pipeline;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Lazy
@Component
public class TheGreatPumpkin {
  private final List<Pipeline> pipelines;

  public TheGreatPumpkin(@Viablity(viable = false) List<Pipeline> pipelines) throws InterruptedException {
    System.out.println("Creating The Great Pumpkin! This takes a while...");
    Thread.sleep(10000);
    this.pipelines = pipelines;
  }

  public void bringAboutAllTheThings() {
    for (Pipeline pipeline : pipelines) {
      pipeline.execute();
    }
  }
}
