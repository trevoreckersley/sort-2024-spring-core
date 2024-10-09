package com.example.spring.core.agent;

import java.util.Map;
import java.util.Set;

import com.example.spring.core.workflow.Pipeline;
import org.springframework.stereotype.Component;

@Component
public class AdminAgent {
  private final Map<String, Pipeline> pipelines;

  public AdminAgent(Map<String, Pipeline> pipelines) {
    this.pipelines = pipelines;
  }

  public void execute(String pipelineName) {
    Pipeline pipeline = pipelines.get(pipelineName);
    if (pipeline == null) {
      System.out.println("No pipeline found with name: " + pipelineName);
    }
    else {
      pipeline.execute();
    }
  }

  public Set<String> availablePipelines() {
    return pipelines.keySet();
  }
}
