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
            Optional<Libretto> libretto = librettoRepository.findByCorsoAndStudente(corsoStudente.getCorso(), studenteRepository.findById(corsoStudente.getStudente()).get())
            if(libretto.isEmpty()) return false else return true
        } catch (Exception e) {
            return false
        }
    }

    String prenotaStudente(Prenotazione prenotazione){
        prenotazioneRepository.save(prenotazione)
    }

    Prenotazione toPrenotazione(PrenotazioneEXT prenotazioneEXT){
        Prenotazione p = new Prenotazione()
        p.setStudente(studenteRepository.findById(prenotazioneEXT.getStudente()).get())
        p.setVoto(prenotazioneEXT.getVoto())
        p.setEdizioneCorso(prenotazioneEXT.getEdizioneCorso())
        p.setCorso(prenotazioneEXT.getCorso())
        p.setDataAppello(prenotazioneEXT.getDataAppello())
        p.setNome(prenotazioneEXT.getNome())
        p.setCodice(prenotazioneEXT.getCodice())
        return p;
    }
}
