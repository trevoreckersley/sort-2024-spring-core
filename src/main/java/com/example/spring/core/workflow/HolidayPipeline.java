package com.example.spring.core.workflow;

import com.example.spring.core.annotations.Viablity;
import com.example.spring.core.factory.SantasWorkshop;
import com.example.spring.core.transport.SantasSleigh;
import org.springframework.stereotype.Component;

@Component
@Viablity(viable = false)
public class HolidayPipeline implements Pipeline {
  private final SantasWorkshop santasWorkshop;
  private final SantasSleigh santasSleigh;

  public HolidayPipeline(SantasWorkshop santasWorkshop, SantasSleigh santasSleigh) {
    this.santasWorkshop = santasWorkshop;
    this.santasSleigh = santasSleigh;
  }

  @Override
  public void execute() {
    System.out.println("Invoking holiday pipeline");
    santasSleigh.handle(santasWorkshop.createItem());
  }
}
