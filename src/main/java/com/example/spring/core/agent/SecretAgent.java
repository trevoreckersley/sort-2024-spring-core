package com.example.spring.core.agent;

import com.example.spring.core.factory.HamburgerShop;
import com.example.spring.core.stats.Counter;
import com.example.spring.core.transport.TimeDilationTransport;
import com.example.spring.core.workflow.DefaultPipeline;
import com.example.spring.core.workflow.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SecretAgent {

  private final Pipeline pipeline;

  @Autowired(required = false)
  public SecretAgent(@Lazy HamburgerShop secretBurgerShop, TimeDilationTransport timeDilationTransport) {
    this(secretBurgerShop, timeDilationTransport, new Counter());
  }

  @Autowired(required = false)
  public SecretAgent(
    @Lazy HamburgerShop secretBurgerShop,
    TimeDilationTransport timeDilationTransport,
    Counter counter) {

    DefaultPipeline defaultPipeline = new DefaultPipeline(secretBurgerShop, timeDilationTransport, counter);
    defaultPipeline.setBeanName("secretAgentPipeline(notABean)");
    this.pipeline = defaultPipeline;
  }

  public void fieldWorkPrerequisite() {
    pipeline.execute();
  }
}
