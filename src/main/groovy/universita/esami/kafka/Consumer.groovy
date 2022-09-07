package universita.esami.kafka

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import universita.esami.ext.NuovoStudente
import universita.esami.service.LibrettoService

@Component
class Consumer {

    @Autowired
    final LibrettoService librettoService

    @KafkaListener(topics = "studenti", groupId = "studenti")
    def consume(Messaggio message) {
        switch (key) {
            case "aggiornaStudente":
                NuovoStudente nuovoStudente = (NuovoStudente) instanceOf(message, NuovoStudente.class)
                librettoService.aggiorna(nuovoStudente)
                break
            default:
                break
        }
    }
}
