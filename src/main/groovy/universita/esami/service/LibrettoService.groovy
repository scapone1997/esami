package universita.esami.service

import org.springframework.stereotype.Service
import universita.esami.domain.Libretto
import universita.esami.domain.Studente
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

    def inizializza(NuovoStudente nuovo){
        studenteService.newStudente(nuovo.matricola)
        Studente studente = studenteService.findStudente(nuovo.matricola)
        nuovo.esami.each {es-> {
                Libretto libretto = new Libretto()
                libretto.corso = es.id
                libretto.studente = studente
                libretto.nome = es.nome
                librettoRepository.save(libretto)
            }
        }
    }
}
