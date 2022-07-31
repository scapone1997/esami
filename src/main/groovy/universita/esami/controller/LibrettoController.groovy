package universita.esami.controller

import groovy.transform.TupleConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
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

    @GetMapping("/libretto/{matricola}")
    ResponseEntity<List<Libretto>> libretto(@PathVariable Integer matricola){
        List<Libretto> list = librettoService.getLibretto(matricola)
        return ResponseEntity.ok().body(list)
    }

    @PutMapping("/libretto")
    ResponseEntity<List<Libretto>> libretto(@RequestBody Libretto libretto){
        librettoService.aggiorna(libretto)
        return ResponseEntity.ok().body("ok")
    }

    @PostMapping("/carica-libretto")
    ResponseEntity<String> inizializza(@RequestBody NuovoStudente iscritto){
        librettoService.inizializza(iscritto)
        return ResponseEntity.ok().body("ok")
    }

}
