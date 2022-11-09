package net.goldbach.streams.kafka;

import net.goldbach.streams.payload.HotBeverageOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic-json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(HotBeverageOrder hotBeverageOrder){
        LOGGER.info(String.format("Coffee-Machine Order message recieved -> %s", hotBeverageOrder.toString()));
        LOGGER.info(String.format("Coffee-Machine Hot-drink produced !! -> %s", hotBeverageOrder.toString()));
    }
}
