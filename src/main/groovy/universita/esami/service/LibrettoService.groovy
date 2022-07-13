package universita.esami.service

import org.springframework.stereotype.Service
import universita.esami.domain.Libretto
import universita.esami.ext.NuovoStudente
import universita.esami.repository.LibrettoRepository

@Service
class LibrettoService {
    LibrettoRepository librettoRepository
    StudenteService studenteService

    LibrettoService(LibrettoRepository librettoRepository, StudenteService studenteService) {
        this.librettoRepository = librettoRepository
        this.studenteService = studenteService
    }

    void inizializza(NuovoStudente nuovo){
        studenteService.newStudente(nuovo.getMatricola())
        nuovo.getCorsi().each {it-> {
                Libretto libretto = new Libretto()
                libretto.corso = it
                libretto.studente = studenteService.findStudente(nuovo.getMatricola())
                librettoRepository.save(libretto)
            }
        }
    }
}
