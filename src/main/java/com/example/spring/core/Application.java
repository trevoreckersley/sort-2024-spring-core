
package com.example.spring.core;

import java.util.Map;

import com.example.spring.core.agent.AdminAgent;
import com.example.spring.core.agent.DefaultAgent;
import com.example.spring.core.agent.MrSerious;
import com.example.spring.core.agent.SecretAgent;
import com.example.spring.core.agent.TheGreatPumpkin;
import com.example.spring.core.factory.ItemFactory;
import com.example.spring.core.factory.SantasWorkshop;
import com.example.spring.core.factory.TireManufacturer;
import com.example.spring.core.workflow.DefaultPipeline;
import com.example.spring.core.workflow.FreshReadReportablePipeline;
import com.example.spring.core.workflow.HolidayPipeline;
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;

public class Application {

  private static final String SEPARATOR = "=====================================";

  public static void main(String[] args) {

    writeSeparator();
    System.out.println("Starting type specific context example");
    writeSeparator();
    blankLine();

    var annotationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    annotationContext.registerShutdownHook();
    annotationContext.addApplicationListener(
      (ContextClosedEvent event) -> System.out.println("Context closed event for annotation context"));

    System.out.println("Context started");
    paddedSeparator();

    System.out.println("About Santa's Workshop");

    SantasWorkshop ogSantasWorkshop = annotationContext.getBean(SantasWorkshop.class);
    System.out.println(ogSantasWorkshop);
    System.out.println(ogSantasWorkshop.createItem().getClass().getName());

    blankLine();
    writeSeparator();
    System.out.println("Starting generic context example");
    writeSeparator();
    blankLine();

    GenericApplicationContext context = new GenericApplicationContext();
    new XmlBeanDefinitionReader(context).loadBeanDefinitions("classpath:beans.xml");
    new GroovyBeanDefinitionReader(context).loadBeanDefinitions("classpath:Beans.groovy");
    new AnnotatedBeanDefinitionReader(context).register(ApplicationConfig.class);
    context.registerBean(TireManufacturer.class, TireManufacturer::new);
    context.getEnvironment().addActiveProfile("managed");
    context.refresh();
    context.registerShutdownHook();
    System.out.println("Context started");

    paddedSeparator();

    System.out.println("'New' Santa's Workshop");
    SantasWorkshop santasWorkshop = context.getBean(SantasWorkshop.class);
    System.out.println(santasWorkshop);
    System.out.println(ogSantasWorkshop.createItem().getClass().getName());

    paddedSeparator();
    System.out.println("Item Factories");
    blankLine();

    Map<String, ItemFactory> factories = context.getBeansOfType(ItemFactory.class);
    factories.forEach((name, factoryBean) -> {
      System.out.println("Factory bean name: " + name);
      System.out.println("Factory bean class: " + factoryBean.getClass().getName());
      System.out.println("Item class: " + factoryBean.createItem().getClass().getName());
      blankLine();
    });

    writeSeparator();
    blankLine();
    System.out.println("Reporting Pipeline");
    FreshReadReportablePipeline freshReadReportablePipeline = context.getBean(FreshReadReportablePipeline.class);
    blankLine();
    System.out.println("Execution 1...");
    freshReadReportablePipeline.execute();
    blankLine();
    System.out.println("Execution 2...");
    freshReadReportablePipeline.execute();
    blankLine();
    System.out.println("Execution 3...");
    freshReadReportablePipeline.execute();
    paddedSeparator();

    System.out.println("Admin agent");
    System.out.println("Admin interactable pipelines: " + context.getBean(AdminAgent.class).availablePipelines());

    blankLine();
    System.out.println("holidayPipeline request (from 'non-viable' qualified pipeline)");
    context.getBean(AdminAgent.class).execute("holidayPipeline");

    blankLine();
    System.out.println("glassPipeline request (from 'viable' qualified pipeline)");
    context.getBean(AdminAgent.class).execute("glassPipeline");
    paddedSeparator();

    System.out.println("Mr. Serious");

    blankLine();
    System.out.println("HolidayPipeline request (from 'non-viable' qualified pipeline; does nothing)");
    context.getBean(MrSerious.class).run(HolidayPipeline.class);

    blankLine();
    System.out.println("DefaultPipeline request (runs only the 'viable' qualified pipeline)");
    context.getBean(MrSerious.class).run(DefaultPipeline.class);
    paddedSeparator();

    System.out.println("The Great Pumpkin");
    context.getBean(TheGreatPumpkin.class).bringAboutAllTheThings();
    paddedSeparator();

    System.out.println("Default Agent");
    context.getBean(DefaultAgent.class).execute();
    paddedSeparator();

    System.out.println("Secret Agent");
    context.getBean(SecretAgent.class).fieldWorkPrerequisite();
    paddedSeparator();
  }

  private static void writeSeparator() {
    System.out.println(SEPARATOR);
  }

  private static void paddedSeparator() {
    blankLine();
    writeSeparator();
    blankLine();
  }

  private static void blankLine() {
    System.out.println();
  }

}
