package universita.esami.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import universita.esami.domain.Prenotazione
import universita.esami.dto.ControlloCorsoStudente
import universita.esami.ext.NuovoStudente;
import universita.esami.service.PrenotazioneService;

@Controller
@RequestMapping(value = "/api", produces = "application/json")
class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService

    @RequestMapping(value = "/esiste-corso-non-verbalizzato", method = RequestMethod.GET)
    ResponseEntity<Boolean> isCorsoNonVerbalizzato(@RequestBody ControlloCorsoStudente corsoStudente){
        Boolean result = prenotazioneService.isCorsoNonVerbalizzato(corsoStudente)
        return ResponseEntity.ok().body(result)
    }

    @RequestMapping(value = "/prenota-studente", method = RequestMethod.POST)
    ResponseEntity<Void> prenotaStudente(@RequestBody Prenotazione prenotazione){
        try {
            prenotazioneService.prenotaStudente(prenotazione)
        } catch (Exception e) {
            println "prenotazione non andata a buon fine " + e.message
            return ResponseEntity.badRequest()
        }
        return ResponseEntity.ok()
    }

    @RequestMapping(value = "/esame-sostenuto", method = RequestMethod.POST)
    ResponseEntity<Prenotazione> esameSostenuto(@RequestBody Prenotazione prenotazione){
        try {
            esameSostenuto(prenotazione)
        } catch (Exception e) {
            println "prenotazione non andata a buon fine " + e.message
            return ResponseEntity.badRequest()
        }
        return ResponseEntity.ok()
    }


}

