package universita.esami.domain

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "studente")
class Studente {

    @Id
    Integer matricola
}
