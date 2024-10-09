package com.example.spring.core.agent;

import com.example.spring.core.workflow.Pipeline;
import org.springframework.stereotype.Component;

@Component
public class DefaultAgent {

  private final Pipeline pipeline;

  public DefaultAgent(Pipeline pipeline) {
    this.pipeline = pipeline;
  }

  public void execute() {
    pipeline.execute();
  }

}
