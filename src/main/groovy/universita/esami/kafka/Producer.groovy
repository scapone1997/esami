package universita.esami.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult

class Producer {
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate

    @Autowired
    ObjectMapper objectMapper

    SendResult<String, String> sendMessaggio(Messaggio messaggio) {
        SendResult<String, String> sendResult = null
        try {
            String key = messaggio.codice
            String value = objectMapper.writeValueAsString(messaggio)
            sendResult = kafkaTemplate.sendDefault(key, value).get()
            println "Messaggio: " + messaggio + " inviato a Kafka."
        } catch (Exception e) {
            println "Eccezione lanciata nel send Kafka: " + e.getClass()
        }
    }
}