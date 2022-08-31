package com.alirizakocas.streamreader.config;

import com.alirizakocas.streamreader.model.ProductViewModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Value("${product.views.kafka.address}")
    private String kafkaAddress;

    @Value("${product.views.kafka.group.id}")
    private String groupId;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ProductViewModel> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ProductViewModel> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, ProductViewModel> consumerFactory() {
        JsonDeserializer<ProductViewModel> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("com.alirizakocas.view.*");
        deserializer.setUseTypeHeaders(false);

        return new DefaultKafkaConsumerFactory<>(kafkaConsumerConfig());
    }

    @Bean
    public Map<String, Object> kafkaConsumerConfig() {

        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, ProductViewModel.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.USE_TYPE_INFO_HEADERS, false);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return props;
    }
}
