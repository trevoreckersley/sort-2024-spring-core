package com.example.spring.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.spring.core.agent.SecretAgent;
import com.example.spring.core.annotations.Viablity;
import com.example.spring.core.factory.Glassworks;
import com.example.spring.core.factory.HamburgerShop;
import com.example.spring.core.factory.ItemFactory;
import com.example.spring.core.factory.SantasWorkshop;
import com.example.spring.core.stats.Counter;
import com.example.spring.core.transport.Carrier;
import com.example.spring.core.transport.Immediate;
import com.example.spring.core.transport.Post;
import com.example.spring.core.workflow.DefaultPipeline;
import com.example.spring.core.workflow.MisfiringPipeline;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration(enforceUniqueMethods = false)
@ImportResource({ "classpath:beans.xml", "classpath:Beans.groovy" })
@ComponentScan
public class ApplicationConfig {

  @Bean
  SantasWorkshop santasWorkshop() {
    return new SantasWorkshop();
  }

  @Bean
  @Viablity
  @Primary
  DefaultPipeline glassPipeline(Glassworks windowFactory, Post post, Optional<Counter> counter) {
    return new DefaultPipeline(windowFactory, post, counter.orElseGet(Counter::new));
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Viablity(viable = false)
  DefaultPipeline randomCombinatoryPipeline(
    ObjectProvider<Carrier> carriers,
    Map<String, ItemFactory> factories,
    Counter counter) {

    Carrier carrier = carriers.stream().collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
      Collections.shuffle(list);
      return list.get(0);
    }));
    List<String> ids = new ArrayList<>(factories.keySet());
    Collections.shuffle(ids);
    String id = ids.get(0);
    ItemFactory factory = factories.get(id);
    System.out.printf("Creating random pipeline of %s and %s\n", factory, carrier);
    return new DefaultPipeline(factory, carrier, counter);
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Viablity(viable = false)
  DefaultPipeline randomCombinatoryPipeline(ObjectProvider<Carrier> carriers, Map<String, ItemFactory> factories) {
    System.out.println("Creating first item 'random' factory");
    return new DefaultPipeline(factories.values().iterator().next(), carriers.iterator().next(), new Counter());
  }

  @Bean
  @Viablity
  DefaultPipeline mikesBurgerBelt(@Autowired(required = false) Counter counter, Immediate immediate) {
    HamburgerShop hamburgerShop = new HamburgerShop("Mike's Burgers", "The Big Mike");
    return new DefaultPipeline(hamburgerShop, immediate, counter == null ? new Counter() : counter);
  }

  @Bean
  FactoryBean<DefaultPipeline> burgerPalacePipeline(
    HamburgerShop burgerPalace,
    @Autowired(required = false) Counter counter,
    Immediate immediate) {

    return new FactoryBean<>() {
      @Override
      public DefaultPipeline getObject() {
        return new DefaultPipeline(burgerPalace, immediate, counter);
      }

      @Override
      public Class<?> getObjectType() {
        return DefaultPipeline.class;
      }
    };
  }

  @Bean
  HamburgerShop secretBurgerShop(SecretAgent secretAgent) {
    System.out.println("Creating a...tire shop...?");
    return new HamburgerShop(
      "Secret Burger",
      "Agent %s's favorite".formatted(secretAgent.toString().replaceFirst(".*@", "")));
  }

  @Viablity(viable = false)
  @Bean(initMethod = "init", destroyMethod = "close")
  MisfiringPipeline misfiringPipeline() {
    return new MisfiringPipeline();
  }

}
