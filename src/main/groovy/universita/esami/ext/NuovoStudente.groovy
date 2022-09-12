package universita.esami.ext

import groovy.transform.ToString
import universita.esami.kafka.Messaggio

@ToString
class NuovoStudente extends Messaggio {
    Integer matricola
    List<EsamiObbligatori> esami
}
