import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * Controleur du clavier
 */
public class ControleurLettres implements EventHandler<ActionEvent> {

    /**
     * modèle du jeu
     */
    private MotMystere modelePendu;
    /**
     * vue du jeu
     */
    private Pendu vuePendu;

    /**
     * @param modelePendu modèle du jeu
     * @param vuePendu vue du jeu
     */
    ControleurLettres(MotMystere modelePendu, Pendu vuePendu){
        // A implémenter  fait
        this.modelePendu= modelePendu;
        this.vuePendu= vuePendu;
    }

    /**nc: Essayer la lettre, mettre à jour l'affichage et vérifier si la partie est finie
     * @param actionEvent l'événement
     * Actions à effectuer lors du clic sur une touche du clavier
     * Il faut do
     */
    @Override
    public void handle(ActionEvent actionEvent) {// regarder en paralele controllancerpartie
        // A implémenter 
        //getNbEssais() perdu()
        Button lettreBouton = (Button) (actionEvent.getSource());
        char lettre= lettreBouton.getText(); 
        //int nbApparition= motMystere.essaiLettre(lettre); //var motMystere a creer?
        //if(getNbLettresRestantes()==0 ){

        }


    }

    