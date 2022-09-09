package universita.esami.controller

import groovy.transform.TupleConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import universita.esami.domain.Libretto
import universita.esami.ext.NuovoStudente
import universita.esami.service.LibrettoService

@Controller
@RequestMapping("api/")
class LibrettoController {

    @Autowired
    LibrettoService librettoService

    @GetMapping("/elenco-esami-da-sostenere/{matricola}")
    ResponseEntity<List<Libretto>> elencoEsamiDaSostenere(@PathVariable Integer matricola){
        List<Libretto> list = librettoService.elencoEsamiDaSostenere(matricola)
        return ResponseEntity.ok().body(list)
    }

    @GetMapping("/libretto/{matricola}")
    ResponseEntity<List<Libretto>> libretto(@PathVariable Integer matricola){
        List<Libretto> list = librettoService.libretto(matricola)
        return ResponseEntity.ok().body(list)
    }

    @PutMapping("/libretto")
    ResponseEntity<String> aggiornaLibretto(@RequestBody Libretto libretto){
        librettoService.aggiorna(libretto)
        return ResponseEntity.ok().body("ok")
    }

    @PostMapping("/carica-libretto")
    ResponseEntity<String> inizializzaLibretto(@RequestBody NuovoStudente iscritto){
        librettoService.inizializza(iscritto)
        return ResponseEntity.ok().body("ok")
    }

    @DeleteMapping("/elimina-libretto")
    ResponseEntity<String> eliminaLibretto(@RequestBody Integer matricola){
        librettoService.eliminaLibretto(matricola);
        return ResponseEntity.ok().body("ok")
    }

}
