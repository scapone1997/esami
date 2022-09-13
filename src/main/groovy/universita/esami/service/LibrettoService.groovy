package universita.esami.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import universita.esami.domain.Libretto
import universita.esami.domain.Prenotazione
import universita.esami.domain.Studente
import universita.esami.ext.NuovoStudente
import universita.esami.repository.LibrettoRepository
import universita.esami.repository.PrenotazioneRepository
import universita.esami.repository.StudenteRepository

import javax.transaction.Transactional

@Service
class LibrettoService {
    @Autowired
    LibrettoRepository librettoRepository
    @Autowired
    StudenteService studenteService
    @Autowired
    StudenteRepository studenteRepository
    @Autowired
    PrenotazioneRepository prenotazioneRepository

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
        println nuovo.toString() + " aggiunto al DB."
    }

    List<Libretto> elencoEsamiDaSostenere(Integer matricola){
        return librettoRepository.findEsamiDaSostenereByStudente(matricola)
    }

    List<Libretto> libretto(Integer matricola){
        return librettoRepository.findLibrettoByMatricola(matricola)
    }

    def aggiorna(Libretto libretto){
        librettoRepository.findByNomeAndStudente(libretto.nome, libretto.studente).ifPresent(
                l->{
                    l.nome = libretto.nome
                    l.edizioneCorso = libretto.edizioneCorso
                    l.corso = libretto.corso
                    l.data = libretto.data
                    l.edizioneCorso = libretto.edizioneCorso
                    l.voto = libretto.voto
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

    Libretto convalidaEsame(Integer corso, Integer studente) throws Exception{
        Studente s = studenteService.findStudente(studente)
        Prenotazione p = prenotazioneRepository.findByStudenteAndCorso(s, corso).get()
        if(p.voto != null ){
            librettoRepository.findByCorsoAndStudente(corso, s)
                    .ifPresent(l->{
                        l.edizioneCorso = p.edizioneCorso
                        l.voto = p.voto
                        l.data = p.dataAppello
                        librettoRepository.save(l)
                    })
        }
        return librettoRepository.findByCorsoAndStudente(corso, studenteRepository.findById(studente).get()).get()
    }
}
