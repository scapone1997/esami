package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import universita.esami.domain.Studente

@Repository
interface StudenteRepository extends JpaRepository<Studente, Integer>{

}