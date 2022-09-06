package universita.esami.service

import org.springframework.stereotype.Service
import universita.esami.domain.Libretto
import universita.esami.domain.Studente
import universita.esami.ext.NuovoStudente
import universita.esami.repository.LibrettoRepository
import universita.esami.repository.StudenteRepository

import javax.transaction.Transactional

@Service
class LibrettoService {
    LibrettoRepository librettoRepository
    StudenteService studenteService

    StudenteRepository studenteRepository

    LibrettoService(LibrettoRepository librettoRepository, StudenteService studenteService, StudenteRepository studenteRepository) {
        this.librettoRepository = librettoRepository
        this.studenteService = studenteService
        this.studenteRepository = studenteRepository
    }

    def inizializza(NuovoStudente nuovo){
        studenteService.newStudente(nuovo.matricola)
        def studente = studenteService.findStudente(nuovo.matricola)
        nuovo.esami.each {es-> {
                Libretto libretto = new Libretto()
                libretto.corso = es.id
                libretto.studente = studente
                libretto.nome = es.nome
                librettoRepository.save(libretto)
            }
        }
    }

    List<Libretto> getLibretto(Integer matricola){
        return librettoRepository.findByStudente(matricola)
    }

    def aggiorna(Libretto libretto){
        librettoRepository.findByNomeAndStudente(libretto.nome, libretto.studente).ifPresent(
                l->{
                    l.setNome(libretto.nome)
                    l.setEdizioneCorso(libretto.edizioneCorso)
                    l.setCorso(libretto.corso)
                    l.setData(libretto.data)
                    l.setEdizioneCorso(libretto.edizioneCorso)
                    l.setVoto(libretto.voto)
                    librettoRepository.save(l)
                }
        )
    }

    @Transactional
    def eliminaLibretto(Integer matricola){
        Optional<Studente> s = studenteRepository.findById(matricola)
        librettoRepository.deleteByStudente(s.get())
        studenteRepository.delete(s.get())
    }
}
