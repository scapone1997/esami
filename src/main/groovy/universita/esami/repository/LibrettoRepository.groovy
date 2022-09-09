package universita.esami.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import universita.esami.domain.Libretto
import universita.esami.domain.Studente

@Repository
interface LibrettoRepository extends JpaRepository<Libretto, Integer>{

    @Query(value = "SELECT * FROM libretto WHERE studente =:matricola", nativeQuery = true)
    List<Libretto> findByStudente(@Param("matricola") Integer matricola)

    Optional<Libretto> findByNomeAndStudente(String nome, Studente studente)

    Optional<Libretto> findByCorsoAndStudente(Integer corso, Studente studente)

    void deleteByNomeAndStudente(String nome, Studente studente)

    void deleteByStudente(Studente studente);
}
