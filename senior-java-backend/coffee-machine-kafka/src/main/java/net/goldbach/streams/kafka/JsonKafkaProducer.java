package net.goldbach.streams.kafka;

import net.goldbach.streams.payload.HotBeverageOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {

    @Value("${spring.kafka.topic-json.name}")
    private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, HotBeverageOrder> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, HotBeverageOrder> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(HotBeverageOrder hotBeverageOrder){

        LOGGER.info(String.format("Message sent -> %s", hotBeverageOrder.toString()));

        Message<HotBeverageOrder> message = MessageBuilder
                .withPayload(hotBeverageOrder)
                .setHeader(KafkaHeaders.TOPIC, topicJsonName)
                .build();

        kafkaTemplate.send(message);
    }
}
