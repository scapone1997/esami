package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import universita.esami.domain.Prenotazione

@Repository
interface PrenotazioneRepository extends  JpaRepository<Prenotazione, Integer>{

}