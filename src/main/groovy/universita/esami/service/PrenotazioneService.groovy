package universita.esami.service

import org.springframework.stereotype.Service
import universita.esami.domain.Libretto
import universita.esami.domain.Prenotazione
import universita.esami.dto.ControlloCorsoStudente
import universita.esami.ext.PrenotazioneEXT
import universita.esami.repository.LibrettoRepository
import universita.esami.repository.PrenotazioneRepository
import universita.esami.repository.StudenteRepository

@Service
class PrenotazioneService {

    LibrettoRepository librettoRepository
    StudenteService studenteService
    StudenteRepository studenteRepository
    PrenotazioneRepository prenotazioneRepository

    PrenotazioneService(LibrettoRepository librettoRepository, StudenteService studenteService, StudenteRepository studenteRepository, PrenotazioneRepository prenotazioneRepository) {
        this.librettoRepository = librettoRepository
        this.studenteService = studenteService
        this.studenteRepository = studenteRepository
        this.prenotazioneRepository = prenotazioneRepository
    }

    Boolean isCorsoNonVerbalizzato(ControlloCorsoStudente corsoStudente){
        try {
            Optional<Libretto> libretto = librettoRepository.findByCorsoAndStudente(corsoStudente.corso, studenteRepository.findById(corsoStudente.studente).get())
            if(libretto.isEmpty()) return false else return true
        } catch (Exception e) {
            return false
        }
    }

    String prenotaStudente(Prenotazione prenotazione) throws Exception{
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
}
