package universita.esami.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import universita.esami.domain.Prenotazione
import universita.esami.ext.NuovoStudente
import universita.esami.ext.PrenotazioneEXT
import universita.esami.service.LibrettoService
import universita.esami.service.PrenotazioneService

@Component
class Consumer {

    @Autowired
    final LibrettoService librettoService

    @Autowired
    final PrenotazioneService prenotazioneService

    @Autowired
    final ObjectMapper objectMapper

    @KafkaListener(topics = "studenti", groupId = "studenti")
    def consume(String message) {
        Messaggio m = objectMapper.readValue(message, Messaggio.class)
        println "messaggio: " + m.codice + " in consumazione."
        switch (m.codice){
            case "attivaStudente":
                librettoService.inizializza(objectMapper.readValue(message, NuovoStudente.class))
                break
            case "prenotaStudente":
                PrenotazioneEXT prenotazioneEXT = objectMapper.readValue(message, PrenotazioneEXT.class);
                Prenotazione prenotazione = prenotazioneService.toPrenotazione(prenotazioneEXT)
                prenotazioneService.prenotaStudente(prenotazione)
                break
        }
    }
}
