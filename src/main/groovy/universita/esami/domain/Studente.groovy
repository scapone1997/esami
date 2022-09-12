package universita.esami.domain

import groovy.transform.ToString

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "studente")
@ToString
class Studente {

    @Id
    Integer matricola
}
