import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;



public class ControleurParametres implements EventHandler<ActionEvent>{

    
    private Pendu pendu;
    
    public ControleurParametres(Pendu pendu){
        this.pendu =pendu;
    }

    @Override 
    public void handle(ActionEvent event){
        Button button = (Button) (event.getSource());
        if (button.getText().contains("Parametres"))
            {this.pendu.modeParametres();}
        else 
            {this.pendu.modeJeu();}
    }
}