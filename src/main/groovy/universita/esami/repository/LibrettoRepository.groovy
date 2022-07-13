package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import universita.esami.domain.Libretto

interface LibrettoRepository extends JpaRepository<Libretto, Integer>{
}
