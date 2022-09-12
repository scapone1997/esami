package universita.esami.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import universita.esami.domain.Libretto
import universita.esami.domain.Prenotazione
import universita.esami.dto.ControlloCorsoStudente
import universita.esami.ext.NuovoStudente
import universita.esami.ext.PrenotazioneEXT;
import universita.esami.service.PrenotazioneService;

@Controller
@RequestMapping(value = "/api", produces = "application/json")
class PrenotazioneController {

    @Autowired
    PrenotazioneService prenotazioneService

    @PostMapping("/esiste-corso-non-verbalizzato")
    @ResponseBody ResponseEntity<?> isCorsoNonVerbalizzato(@RequestBody ControlloCorsoStudente corsoStudente){
        Boolean result = prenotazioneService.isCorsoNonVerbalizzato(corsoStudente)
        return ResponseEntity.ok().body(result)
    }

    @PostMapping("/prenota-studente")
    ResponseEntity<Void> prenotaStudente(@RequestBody Prenotazione prenotazione){
        try {
            prenotazioneService.prenotaStudente(prenotazione)
        } catch (Exception e) {
            println "prenotazione non andata a buon fine " + e.message
            return ResponseEntity.badRequest()
        }
        return ResponseEntity.ok()
    }

    @PostMapping("/esame-sostenuto")
    ResponseEntity<String> esameSostenuto(@RequestBody PrenotazioneEXT prenotazione){
        try {
            prenotazioneService.esameSostenuto(prenotazione)
        } catch (Exception e) {
            println "prenotazione non andata a buon fine " + e.message
            return ResponseEntity.ok().body("Not found.")
        }
        return ResponseEntity.ok().body("Esame convalidato. ")
    }

    @GetMapping("/pulizia-prenotazioni")
    ResponseEntity<List<Libretto>> puliziaPrenotazioniBocciati(){
        return ResponseEntity.ok().body(prenotazioneService.cancellaPrenotazioniBocciati())
    }

    @GetMapping("/pulizia-esami-convalidati")
    ResponseEntity<List<Libretto>> puliziaPrenotazioniPromossi(){
        return ResponseEntity.ok().body(prenotazioneService.cancellaPrenotazioniPromossi())
    }
}

