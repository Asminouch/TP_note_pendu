import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle ;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Génère la vue d'un clavier et associe le contrôleur aux touches
 * le choix ici est d'un faire un héritié d'un TilePane
 */
public class Clavier extends TilePane{
    /**
     * il est conseillé de stocker les touches dans un ArrayList
     */
    private List<Button> clavier;

    //rajouter par moi
    private EventHandler<ActionEvent> actionTouches;
    private int tailleLigne;

    /**
     * constructeur du clavier
     * @param touches une chaine de caractères qui contient les lettres à mettre sur les touches
     * @param actionTouches le contrôleur des touches
     * @param tailleLigne nombre de touches par ligne
     */
    public Clavier(String touches, EventHandler<ActionEvent> actionTouches) {
  
        this.clavier= new ArrayList<>();
        this.actionTouches= actionTouches;
        this.tailleLigne= 8;
       

        for(int i=0; i<touches.length(); i++){ //String lettre: touches
            Button toucheLettre= new Button(Character.toString(touches.charAt(i)));
            toucheLettre.setOnAction(this.actionTouches);
            this.clavier.add(toucheLettre);
            this.getChildren().add(toucheLettre);
        }
        this.setPrefColumns(this.tailleLigne);
        this.setHgap(5);
        this.setVgap(5);
        this.setAlignment(Pos.CENTER);
        
    }

    /**
     * permet de désactiver certaines touches du clavier (et active les autres)
     * @param touchesDesactivees une chaine de caractères contenant la liste des touches désactivées
     */
    public void desactiveTouches(Set<String> touchesDesactivees){
        for(Button touche: this.clavier){
            String lettre= touche.getText();
            boolean desactive= (touchesDesactivees!= null) && touchesDesactivees.contains(lettre);
            touche.setDisable(desactive);

        }
        
         


    }
}
