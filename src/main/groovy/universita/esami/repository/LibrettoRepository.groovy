package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import universita.esami.domain.Libretto
import universita.esami.domain.Studente

@Repository
interface LibrettoRepository extends JpaRepository<Libretto, Integer>{

    @Query(value = "SELECT * FROM esami.libretto l WHERE l.studente=:matricola and l.voto is NULL", nativeQuery = true)
    List<Libretto> findEsamiDaSostenereByStudente(@Param("matricola") Integer matricola)

    @Query(value = "SELECT * FROM esami.libretto l WHERE l.studente=:matricola and l.voto is not NULL", nativeQuery = true)
    List<Libretto> findLibrettoByMatricola(@Param("matricola") Integer matricola)

    Optional<Libretto> findByNomeAndStudente(String nome, Studente studente)

    Optional<Libretto> findByCorsoAndStudente(Integer corso, Studente studente)

    void deleteByStudente(Studente studente);
}
