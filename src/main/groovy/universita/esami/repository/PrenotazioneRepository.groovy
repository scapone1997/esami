package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import universita.esami.domain.Prenotazione
import universita.esami.domain.Studente

@Repository
interface PrenotazioneRepository extends JpaRepository<Prenotazione, Integer>{

    Optional<Prenotazione> findByStudenteAndCorso(Studente s, Integer c)
}
