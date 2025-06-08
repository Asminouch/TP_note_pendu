import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


/**
 * Permet de gérer un Text associé à une Timeline pour afficher un temps écoulé
 */
public class Chronometre extends Text{
    /**
     * timeline qui va gérer le temps
     */
    private Timeline timeline;
    /**
     * la fenêtre de temps
     */
    private KeyFrame keyFrame;
    /**
     * le contrôleur associé au chronomètre
     */
    private ControleurChronometre actionTemps;
    /**
     * vue du jeu
     */
    private Pendu pendu;

    /**
     * Chronomètre commence a 2 min
     */
    private final long dureeDebut= 2*60*1000; //=2min

    /**
     * Constructeur permettant de créer le chronomètre
     * avec un label initialisé à "0:0:0"
     * Ce constructeur créer la Timeline, la KeyFrame et le contrôleur
     */
    public Chronometre(Pendu p){
        this.setFont(new Font("Arial", 18));
        this.pendu=p;

        this.actionTemps = new ControleurChronometre(this);
        this.keyFrame = new KeyFrame(Duration.seconds(1), this.actionTemps); // rafraîchit chaque seconde
        this.timeline = new Timeline(this.keyFrame);
        this.timeline.setCycleCount(Animation.INDEFINITE);

    }
 
    /**
     * Permet au controleur de mettre à jour le text
     * la durée est affichée sous la forme m:s
     * @param tempsMillisec la durée depuis à afficher
     */
    public void setTime(long tempsMillisec){
        long tempsRestant = dureeDebut - tempsMillisec;

        if (tempsRestant <= 0) {
            this.setText("0 min 0 s");
            this.stop();
            if(this.pendu== null){
                pendu.popUpMessagePerdu();
            }


            return; // Ne pas continuer si le temps est écoulé
        }

        long totalSeconde = tempsRestant / 1000;
        long min = totalSeconde / 60;
        long sec = totalSeconde % 60;

        this.setText(min + " min " + sec + " s");
    }

    /**
     * Permet de démarrer le chronomètre
     */
    public void start(){
        this.actionTemps.reset();
        this.timeline.play();
    }

    /**
     * Permet d'arrêter le chronomètre
     */
    public void stop(){
        this.timeline.stop();
    }

    /**
     * Permet de remettre le chronomètre à 0
     */
    public void resetTime(){
        this.actionTemps.reset();
    }
}
