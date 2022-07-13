package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import universita.esami.domain.Studente

interface StudenteRepository extends JpaRepository<Studente, Integer>{

}