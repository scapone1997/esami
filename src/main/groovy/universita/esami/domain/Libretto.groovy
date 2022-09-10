package universita.esami.domain

import groovy.transform.ToString

import javax.persistence.*

@Entity
@Table(name = "libretto")
@ToString
class Libretto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id
    @Column(name="edizione_corso")
    Integer edizioneCorso
    Integer corso
    @Column(name="data_appello")
    Date data
    String nome
    String voto
    @ManyToOne
    @JoinColumn(name = "studente")
    Studente studente
}
