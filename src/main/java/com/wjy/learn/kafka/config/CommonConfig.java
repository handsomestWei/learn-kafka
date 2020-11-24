package com.wjy.learn.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

@Configuration
@Import(CommonProperties.class)
public class CommonConfig {

    private CommonProperties pro;

    CommonConfig(CommonProperties pro) {
        this.pro = pro;
    }

    @Bean
    public RecordMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

    @Bean
    public NewTopic testTopic() {
        return new NewTopic(pro.getTopic(), pro.getNumPartitions(), pro.getReplicationFactor());
    }

}
