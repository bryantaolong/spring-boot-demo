package com.bryan.springbootdemo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.lang.NonNull;

/**
 * ClassName: ElasticsearchConfig
 * Package: com.bryan.springbootdemo.config
 * Description:
 * Author: Bryan Long
 * Create: 2025/1/2 - 15:53
 * Version: v1.0
 */
@Configuration
@RequiredArgsConstructor
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    @Override
    @NonNull // This annotation aims to fix: Not annotated method overrides method annotated with @NonNullApi
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();
    }

//    @Bean
//    @Override
//    @NonNull
//    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
////        use @WritingConverter and @ReadingConverter to register converters
//        return new ElasticsearchCustomConversions();
//    }

}
