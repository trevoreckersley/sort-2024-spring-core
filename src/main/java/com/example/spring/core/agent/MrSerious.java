package com.example.spring.core.agent;

import java.util.List;

import com.example.spring.core.annotations.Viablity;
import com.example.spring.core.workflow.Pipeline;
import org.springframework.stereotype.Component;

@Component
public class MrSerious {
  private final List<Pipeline> pipelines;

  public MrSerious(@Viablity List<Pipeline> pipelines) {
    this.pipelines = pipelines;
  }

  public void run(Class<? extends Pipeline> type) {
    pipelines.stream()
      .filter(type::isInstance)
      .forEach(Pipeline::execute);
  }
}
