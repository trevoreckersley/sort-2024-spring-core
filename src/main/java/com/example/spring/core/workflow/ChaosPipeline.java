package com.example.spring.core.workflow;

import com.example.spring.core.annotations.Viablity;
import com.example.spring.core.factory.RandomFactory;
import com.example.spring.core.transport.TimeDilationTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Viablity(viable = false)
@Order
public class ChaosPipeline implements Pipeline {

  private RandomFactory randomFactory;
  private TimeDilationTransport transport;

  @Override
  public void execute() {
    System.out.println("Executing chaos pipeline");
    transport.handle(randomFactory.createItem());
  }

  @Autowired
  public void setRandomFactory(RandomFactory randomFactory) {
    this.randomFactory = randomFactory;
  }

  @Autowired
  public void setTransport(TimeDilationTransport transport) {
    this.transport = transport;
  }
}
