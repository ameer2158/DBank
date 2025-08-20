package com.digitalbank.transaction;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaTransactionProducer {
    private static final Logger log = LoggerFactory.getLogger(KafkaTransactionProducer.class);
    private static final String TOPIC = "bank.transactions.transfer";

    private final KafkaTemplate<String, TransactionEvent> template;

    public KafkaTransactionProducer(KafkaTemplate<String, TransactionEvent> template) {
        this.template = template;
    }

    public void publish(TransactionEvent event) {
        try {
            template.send(TOPIC, event.getTransaction_id(), event)
                    .whenComplete((result, ex) -> {
                        if (ex != null) {
                            log.warn("Kafka publish failed (non-blocking). eventId={}", (Object) event.getTransaction_id(), Optional.of(ex));
                        } else {
                            var m = result.getRecordMetadata();
                            log.info("Published transfer event {} to {}-{}@{}",
                                    event.getTransaction_id(), m.topic(), m.partition(), m.offset());
                        }
                    });
        } catch (Exception e) {
            log.warn("Kafka unavailable, skipping publish (suppressed). eventId={}", event.getTransaction_id(), e);
        }
    }
}