import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

//Classe crée par moi 

public class ControleurParametres{

    //pas à mettre ici mais fait
    Image imgParam= new image("../img/parametres.png");
    ImageView view = new ImageView(imgParam);
    Button boutonParametres = new Button();
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
            {this.pendu.fenetreAccueil();}
        else 
            {this.pendu.fenetreJeu();}
    }
}