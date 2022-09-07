package universita.esami.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import universita.esami.ext.NuovoStudente
import universita.esami.service.LibrettoService

@Component
class Consumer {

    @Autowired
    final LibrettoService librettoService

    @Autowired
    final ObjectMapper objectMapper

    @KafkaListener(topics = "studenti", groupId = "studenti")
    def consume(String message) {
        Messaggio m = objectMapper.readValue(message, Messaggio.class)
        if(m.getCodice().equals("attivaStudente")){
            librettoService.inizializza(objectMapper.readValue(message, NuovoStudente.class))
        }

    }
}
