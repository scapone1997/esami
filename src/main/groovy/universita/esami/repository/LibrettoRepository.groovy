package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import universita.esami.domain.Libretto

@Repository
interface LibrettoRepository extends JpaRepository<Libretto, Integer>{
}
