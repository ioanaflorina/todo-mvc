package com.todo.mvc.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"com.todo.mvc"})
@PropertySource("classpath:config/${env}.properties")
public class ProjectConfiguration {
}
