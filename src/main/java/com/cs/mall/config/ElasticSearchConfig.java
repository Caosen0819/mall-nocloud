package com.cs.mall.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Caosen
 * @Date 2022/8/15 14:05
 * @Version 1.0
 */
@Configuration
public class ElasticSearchConfig {
    //注入ioc容器

    @Bean
    public ElasticsearchClient  elasticsearchClient(){
        // Create the low-level client
        RestClient restclient = RestClient.builder(new HttpHost("localhost", 9200)).build();
        // Create the transport with a Jackson mapper
        RestClientTransport transport = new RestClientTransport(restclient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);
        return client;

    }
}
