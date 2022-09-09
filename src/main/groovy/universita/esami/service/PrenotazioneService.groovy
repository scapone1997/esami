package universita.esami.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import universita.esami.domain.Libretto
import universita.esami.domain.Prenotazione
import universita.esami.dto.ControlloCorsoStudente
import universita.esami.ext.PrenotazioneEXT
import universita.esami.kafka.Producer
import universita.esami.repository.LibrettoRepository
import universita.esami.repository.PrenotazioneRepository
import universita.esami.repository.StudenteRepository

@Service
class PrenotazioneService {

    @Autowired
    LibrettoRepository librettoRepository
    @Autowired
    StudenteService studenteService
    @Autowired
    StudenteRepository studenteRepository
    @Autowired
    PrenotazioneRepository prenotazioneRepository
    @Autowired
    Producer producer

    Boolean isCorsoNonVerbalizzato(ControlloCorsoStudente corsoStudente){
        try {
            Optional<Libretto> libretto = librettoRepository.findByCorsoAndStudente(corsoStudente.corso, studenteRepository.findById(corsoStudente.studente).get())
            if(libretto.isEmpty()) return false else return true
        } catch (Exception e) {
            return false
        }
    }

    def prenotaStudente(Prenotazione prenotazione) throws Exception{
        try {
            prenotazioneRepository.save(prenotazione)
            println "prenotazione: " + prenotazione + "scritta nel DB"
        } catch (Exception e) {
            String r = "salvataggio prenotazione non andato a buon fine "; println r
            throw new Exception(r)
        }
    }

    Prenotazione toPrenotazione(PrenotazioneEXT prenotazioneEXT){
        Prenotazione p = new Prenotazione()
        p.studente = studenteRepository.findById(prenotazioneEXT.studente).get()
        p.voto = prenotazioneEXT.voto
        p.edizioneCorso = prenotazioneEXT.edizioneCorso
        p.corso = prenotazioneEXT.corso
        p.dataAppello = prenotazioneEXT.dataAppello
        p.nome = prenotazioneEXT.nome
        p.codice = prenotazioneEXT.codice
        return p;
    }

    PrenotazioneEXT toPrenotazioneEXT(Prenotazione prenotazione){
        PrenotazioneEXT p = new PrenotazioneEXT()
        p.studente = prenotazione.studente.matricola
        p.voto = prenotazione.voto
        p.edizioneCorso = prenotazione.edizioneCorso
        p.corso = prenotazione.corso
        p.dataAppello = prenotazione.dataAppello
        p.nome = prenotazione.nome
        p.codice = prenotazione.codice
        return p;
    }

    Prenotazione esameSostenuto(PrenotazioneEXT prenotazioneEXT){
        Prenotazione p = toPrenotazione(prenotazioneEXT)
        if(p.voto != null){
            prenotazioneRepository.save(p)
            PrenotazioneEXT esameConcluso = toPrenotazioneEXT(p)
            esameConcluso.codice = "esameConcluso"
            try {
                producer.sendMessaggio(esameConcluso)
            } catch (Exception e) {
                println "Errore nell'invio messaggio a Kafka"
                throw new Exception("Errore nell'invio messaggio a Kafka")
            }
            //aggiornare libretto
            //cancellare prenotazione
        }
    }
}
