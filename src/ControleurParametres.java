import javafx.event.EventHandler;
import javafx.event.ActionEvent ;
import javafx.scene.control.Button;

public class ControleurParametres{
    
    private Pendu pendu;
    
    public ControleurParametres(Pendu pendu){
        this.pendu =pendu;
    }
    
    @Override// code du tp3 controle connexion
    public void handle(ActionEvent event){
        Button button = (Button) (event.getSource());
        if (button.getText().contains("D"))
            this.appli.afficheFenetre2();
        else 
            this.appli.afficheFenetre1();
    }
}