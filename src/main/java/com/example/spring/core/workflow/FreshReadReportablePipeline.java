package com.example.spring.core.workflow;

import com.example.spring.core.annotations.Viablity;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
@Viablity(viable = false)
public class FreshReadReportablePipeline implements Pipeline {
  @Override
  public void execute() {
    Pipeline pipeline = pipeline();
    System.out.println("Executing pipeline " + pipeline);
    pipeline.execute();
  }

  @Lookup("randomCombinatoryPipeline")
  protected Pipeline pipeline() {
    return null;
  }

  @PostConstruct
  void init() {
    System.out.println("Initializing reportable pipeline");
  }

  @PreDestroy
  void close() {
    System.out.println("Closing reportable pipeline");
  }
}
