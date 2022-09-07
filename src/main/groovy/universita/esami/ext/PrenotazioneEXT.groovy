package universita.esami.ext
import universita.esami.kafka.Messaggio


class PrenotazioneEXT extends Messaggio{
    Integer edizioneCorso
    Integer corso
    Date dataAppello
    String nome
    Integer voto
    Integer studente
}