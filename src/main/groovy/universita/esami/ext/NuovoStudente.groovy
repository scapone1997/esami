package universita.esami.ext

import universita.esami.kafka.Messaggio

class NuovoStudente extends Messaggio {
    Integer matricola
    List<EsamiObbligatori> esami
}
