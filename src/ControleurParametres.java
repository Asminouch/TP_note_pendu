import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

//Classe crée par moi 

public class ControleurParametres implements EventHandler<ActionEvent>{

    
    //boutonParametres.setGraphic(view);  //Set graphic quepour les HBOX donc voir comment faire pour les autres Boutons du haut  
    private Pendu pendu;
    
    public ControleurParametres(Pendu pendu){
        this.pendu =pendu;
    }
    // code du tp3 controle connexion

    @Override 
    public void handle(ActionEvent event){
        Button button = (Button) (event.getSource());
        if (button.getText().contains("Parametres"))//parametre surment pas ecrit comme cca 
            {this.pendu.modeParametres();}
        else 
            {this.pendu.modeJeu();}
    }
}