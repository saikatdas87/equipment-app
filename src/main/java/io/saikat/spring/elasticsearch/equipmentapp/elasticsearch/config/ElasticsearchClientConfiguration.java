package io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient;
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients;
import org.springframework.data.elasticsearch.config.AbstractReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

import java.net.InetAddress;
import java.net.InetSocketAddress;

@Configuration
@EnableReactiveElasticsearchRepositories(basePackages = "io.saikat.spring.elasticsearch.equipmentapp.elasticsearch.repository")
@ComponentScan(basePackages = { "io.saikat.spring.elasticsearch.equipmentapp.service" })
public class ElasticsearchClientConfiguration extends AbstractReactiveElasticsearchConfiguration {

  @Value("${elasticsearch.host}")
  private String esHost;

  @Value("${elasticsearch.port}")
  private int esPort;


  @SneakyThrows
  @Bean
  public ReactiveElasticsearchClient reactiveElasticsearchClient() {
    ClientConfiguration clientConfiguration
        = ClientConfiguration.builder()
        .connectedTo(new InetSocketAddress(InetAddress.getByName(esHost), esPort))
        .build();
    return ReactiveRestClients.create(clientConfiguration);
  }

  @Bean
  public ReactiveElasticsearchOperations elasticsearchOperations() {
    return new ReactiveElasticsearchTemplate(reactiveElasticsearchClient());
  }

}
