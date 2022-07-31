package universita.esami.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import universita.esami.domain.Studente
import universita.esami.repository.StudenteRepository

@Service
class StudenteService {

    @Autowired
    StudenteRepository repository

    def newStudente(Integer matricola){
        Studente studente = new Studente()
        studente.matricola = matricola
        repository.save(studente)
    }

    Studente findStudente(Integer matricola){
        Optional<Studente> studente = repository.findById(matricola)
        return studente.get()
    }
}
