package net.goldbach.streams.controller;

import net.goldbach.streams.kafka.JsonKafkaProducer;
import net.goldbach.streams.payload.HotBeverageOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coffee-machine")
public class JsonMessageController {

    private JsonKafkaProducer kafkaProducer;

    public JsonMessageController(JsonKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/order-publish")
    public ResponseEntity<String> publish(@RequestBody HotBeverageOrder hotBeverageOrder){
        kafkaProducer.sendMessage(hotBeverageOrder);
        return ResponseEntity.ok("Json message sent to kafka topic");
    }
}
