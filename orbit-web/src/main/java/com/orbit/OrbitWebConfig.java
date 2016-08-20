package com.orbit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;


@Configuration
@ComponentScan
@EnableSpringConfigured
@EnableAspectJAutoProxy
@PropertySources({
        @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = false)
})
public class OrbitWebConfig {
  /**
   * Enable property placeholder.
   *
   * @return
   */
  @Bean
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  /**
   * Enable method validation, this enable by new features in bean validation specs.
   *
   * @return
   */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
    return postProcessor;
  }
}
