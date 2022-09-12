package universita.esami.ext

import groovy.transform.ToString
import universita.esami.kafka.Messaggio

@ToString
class PrenotazioneEXT extends Messaggio{
    Integer edizioneCorso
    Integer corso
    Date dataAppello
    String nome
    Integer voto
    Integer studente
}