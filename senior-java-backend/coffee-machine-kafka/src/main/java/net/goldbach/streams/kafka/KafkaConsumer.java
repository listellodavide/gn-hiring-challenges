package net.goldbach.streams.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class KafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) throws InterruptedException {
        LOGGER.info(String.format("Coffee-Machine Order received -> %s", message));
        TimeUnit.SECONDS.sleep(1);
        LOGGER.info(String.format("Coffee-Machine Hot-Beverage produced -> %s", message));
    }
}
