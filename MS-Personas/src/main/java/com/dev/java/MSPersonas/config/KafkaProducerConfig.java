package com.dev.java.MSPersonas.config;


import com.dev.java.MSPersonas.dto.NewUserWithProductDTO;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Bean
    public NewTopic generateTopic() {
        return TopicBuilder.name("newUserCreatedTopic")
                .partitions(2)
                .build();
    }

    @Bean
    public NewTopic generateHealthCheckTopic() {
        return TopicBuilder.name("healthCheckTopic")
                .partitions(2)
                .build();
    }

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        return new KafkaAdmin(configs);
    }

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return properties;
    }

    @Bean
    public ProducerFactory<String, String> producerFactoryString() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateHealthCheck() {
        return new KafkaTemplate<>(producerFactoryString());
    }

    /*@Bean
    public ProducerFactory<String, NewUserWithProductDTO> producerFactoryDTO() {
        Map<String, Object> props = new HashMap<>(producerConfig());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, NewUserWithProductDTO> kafkaTemplateNewUserDTO() {
        return new KafkaTemplate<>(producerFactoryDTO());
    }*/

    @Bean
    public KafkaTemplate<String, String> kafkaTemplateNewUserDTO() {
        return new KafkaTemplate<>(producerFactoryString());
    }



}
