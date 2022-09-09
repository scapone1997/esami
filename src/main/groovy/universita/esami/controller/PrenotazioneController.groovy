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
@RequestMapping("api/")
class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService

    @GetMapping("/esiste-corso-non-verbalizzato")
    ResponseEntity<Boolean> isCorsoNonVerbalizzato(@RequestBody ControlloCorsoStudente corsoStudente){
        Boolean result = prenotazioneService.isCorsoNonVerbalizzato(corsoStudente)
        return ResponseEntity.ok().body(result)
    }

    @RequestMapping(value = "/prenota-studente", method = RequestMethod.POST)
    ResponseEntity<String> prenotaStudente(@RequestBody Prenotazione prenotazione){
        try {
            String result = prenotaStudente(prenotazione)
        } catch (Exception e) {
            println "prenotazione non andata a buon fine " + e.message
            return ResponseEntity.badRequest().body(result)
        }
        return ResponseEntity.ok().body(result)
    }
}

