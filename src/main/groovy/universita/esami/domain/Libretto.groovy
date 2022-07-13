package universita.esami.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "libretto")
class Libretto {

    @Id
    Integer id
    @Column(name="edizione_corso")
    Integer edizioneCorso
    Integer corso
    @Column(name="data_appello")
    Date dataAppello
    String voto

    @ManyToOne
    @JoinColumn(name = "studente")
    Studente studente
}
