package universita.esami.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import universita.esami.ext.NuovoStudente
import universita.esami.service.LibrettoService

@Controller
@RequestMapping("api/")
class LibrettoController {

    LibrettoService librettoService

    LibrettoController(LibrettoService librettoService) {
        this.librettoService = librettoService
    }

    @PostMapping("/nuovo-libretto")
    ResponseEntity<NuovoStudente> inizializza(@RequestBody NuovoStudente iscritto){
        librettoService.inizializza(iscritto)
        return ResponseEntity.ok().body(iscritto)
    }

}
