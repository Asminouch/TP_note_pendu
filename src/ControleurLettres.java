import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

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
        String lettre= lettreBouton.getText(); 
        lettreBouton.setDisable(true);
        int nbApparition= this.modelePendu.essaiLettre(lettre.charAt(0));
        this.vuePendu.motCrypte.setText(this.modelePendu.getMotCrypte());
        
        int nbErreur= this.modelePendu.getNbErreursMax()-this.modelePendu.getNbErreursRestants();
        double progression =(double) nbErreur / this.modelePendu.getNbErreursMax();
        this.vuePendu.pg.setProgress(progression);

        Image pendu =this.vuePendu.lesImages.get(nbErreur);
        this.vuePendu.dessin.setImage(pendu);

        if(this.modelePendu.perdu()) {
            Alert perdu = this.vuePendu.popUpMessagePerdu();
            perdu.showAndWait();
            this.vuePendu.modeAccueil();
        }else if(this.modelePendu.gagne()) {
            Alert gagne = this.vuePendu.popUpMessageGagne();
            gagne.showAndWait();
            this.vuePendu.modeAccueil();
        }

    }
}

    