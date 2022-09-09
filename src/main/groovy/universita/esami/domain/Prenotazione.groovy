package universita.esami.domain

import lombok.ToString
import universita.esami.kafka.Messaggio

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "prenotazione", uniqueConstraints = [@UniqueConstraint(columnNames = ["corso", "studente"])])
@ToString
class Prenotazione extends Messaggio{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id
    @Column(name = "edizione_corso")
    Integer edizioneCorso
    Integer corso
    @Column(name = "data_appello")
    Date dataAppello
    String nome
    Integer voto
    @ManyToOne
    @JoinColumn(name = "studente")
    Studente studente
}
