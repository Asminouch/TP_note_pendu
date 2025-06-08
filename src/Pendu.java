import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.scene.control.ButtonBar.ButtonData ;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.List;
import java.util.Timer;


//import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;


import java.util.Arrays;
import java.beans.VetoableChangeListener;
import java.io.File;
import javafx.scene.control.Label;
import java.util.ArrayList;


/**
 * Vue du jeu du pendu
 */
public class Pendu extends Application {
    /**
     * modèle du jeu
     **/
    private MotMystere modelePendu;
    /**
     * Liste qui contient les images du jeu
     */
    public ArrayList<Image> lesImages;
    /**
     * Liste qui contient les noms des niveaux
     */    
    public List<String> niveaux;

    // les différents contrôles qui seront mis à jour ou consultés pour l'affichage
    /**
     * le dessin du pendu
     */
    public ImageView dessin;
    /**
     * le mot à trouver avec les lettres déjà trouvé
     */
    public Text motCrypte;
    /**
     * la barre de progression qui indique le nombre de tentatives
     */
    public ProgressBar pg;
    /**
     * le clavier qui sera géré par une classe à implémenter
     */
    private Clavier clavier;
    /**
     * le text qui indique le niveau de difficulté
     */
    private Text leNiveau;
    /**
     * le chronomètre qui sera géré par une clasee à implémenter
     */
    private Chronometre chrono;
    /**
     * le panel Central qui pourra être modifié selon le mode (accueil ou jeu)
     */
    private BorderPane panelCentral;
    /**
     * le bouton Paramètre / Engrenage
     */
    private Button boutonParametres;
    /**
     * le bouton Accueil / Maison
     */    
    private Button boutonMaison;
    /**
     * le bouton qui permet de (lancer ou relancer une partie
     */ 
    private Button bJouer;
    /**
     * le bouton qui permet de voir les règles du jeu
     */ 
    private Button boutonInfo;
    /**
     * Permet de grouper les radios boutons
     */ 
    private ToggleGroup grpDifficulte;
    /**
     * le bouton qui permet de changer de mot durant une partie
     */ 
    private Button nvMot;


    /**
     * initialise les attributs (créer le modèle, charge les images, crée le chrono ...)
     */
    @Override
    public void init() {
        ///usr/share/dict/french"
        this.modelePendu = new MotMystere("src/dico.txt", 3, 10, MotMystere.FACILE, 10);
        this.lesImages = new ArrayList<Image>();
        this.chargerImages("./img");
        // A terminer d'implementer
        //accueil
        this.boutonMaison= new Button();
        RetourAccueil controleurAccueil= new RetourAccueil(modelePendu, this);
        this.boutonMaison.setOnAction(controleurAccueil);
        //paramètres
        this.boutonParametres= new Button();
        ControleurParametres controleurParam= new ControleurParametres(this);
        this.boutonParametres.setOnAction(controleurParam);
        //lancer Partie
        this.bJouer= new Button("Lancer une partie");
        ControleurLancerPartie controleurPartie= new ControleurLancerPartie(this.modelePendu,this);
        this.bJouer.setOnAction(controleurPartie);

        this.nvMot= new Button("Nouveau mot");
        ControleurLancerPartie controleurChangerMot = new ControleurLancerPartie(this.modelePendu,this);
        this.nvMot.setOnAction(controleurChangerMot);

        this.boutonInfo= new Button();        
        ControleurInfos controleurInfos=new ControleurInfos(this);
        this.boutonInfo.setOnAction(controleurInfos);
        
        this.panelCentral= new BorderPane();
        this.niveaux=new ArrayList<>();
        this.grpDifficulte= new ToggleGroup();
        this.pg= new ProgressBar();

        EventHandler<ActionEvent> actionClavier = new ControleurLettres(this.modelePendu, this);

        this.clavier= new Clavier("ABCDEFGHIJKLMNOPQRSTUVWXYZ-", actionClavier);
        this.chrono= new Chronometre(this);
        this.leNiveau= new Text();
    }
    public MotMystere getModelPendu(){
        return this.modelePendu;
    }

    /**
     * @return  le graphe de scène de la vue à partir de methodes précédantes
     */
    private Scene laScene(){
        BorderPane fenetre = new BorderPane();
        fenetre.setTop(this.titre());

        fenetre.setCenter(this.panelCentral);
        
    

        return new Scene(fenetre, 500, 700); //(fenetre, 800, 1000)
    }

    

    /**
     * @return le panel contenant le titre du jeu
     */
    private Pane titre(){
        // enHaut = new BorderPane();       
        //Pane banniere = new Pane();
        BorderPane banniere = new BorderPane();
        Text text= new Text("Jeu du pendu");
        text.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        banniere.setLeft(text);
        HBox boutons= this.troisBouton();
        banniere.setRight(boutons);
        banniere.setAlignment(text,Pos.CENTER_LEFT);
        banniere.setPadding(new Insets(20));
        banniere.setStyle("-fx-background-color:rgb(212, 208, 231)");
        return banniere;
    }
    private HBox troisBouton(){
        HBox bouton= new HBox();

        //image accueil
        Image imgAccueil = new Image("file:img/home.png");
        ImageView vueAccueil= new ImageView(imgAccueil);
        this.boutonMaison.setGraphic(vueAccueil);
        vueAccueil.setFitHeight(30);
        vueAccueil.setFitWidth(30);

        //image Parametre
        Image imgParam= new Image("file:img/parametres.png");
        ImageView viewParam = new ImageView(imgParam);
        this.boutonParametres.setGraphic(viewParam);
        viewParam.setFitHeight(30);
        viewParam.setFitWidth(30);

        //image Information
        Image imgInfo= new Image("file:img/info.png");
        ImageView vueInfo = new ImageView(imgInfo);
        
        this.boutonInfo.setGraphic(vueInfo);
        vueInfo.setFitHeight(30);
        vueInfo.setFitWidth(30);

        Insets margeBouton = new Insets(20, 5, 20, 5);//ordre: haut, droite, bas, gauche
        HBox.setMargin(this.boutonMaison, margeBouton);
        HBox.setMargin(this.boutonParametres, margeBouton);
        HBox.setMargin(this.boutonInfo, margeBouton);

        bouton.getChildren().addAll(this.boutonMaison, this.boutonParametres, this.boutonInfo);
        return bouton;

    }
    
    
    /**
     * @return le panel du chronomètre
     */
    private TitledPane leChrono(){
        //A implementer
        VBox chrono= new VBox();
        TitledPane leChrono = new TitledPane("Chronomètre",this.chrono );
        leChrono.setCollapsible(false);
        return leChrono;
    }

    // /**
     // * @return la fenêtre de jeu avec le mot crypté, l'image, la barre
     // *         de progression et le clavier
     // */
    private Pane fenetreJeu(){
        
        BorderPane jeu= new BorderPane();

        VBox vboxGauche= new VBox(10); //5= espacement
        Text motATrouver=this.motCrypte;
        
        //image Pendu en cours
        int i=0;
        //Image imgPendu = new Image("file:img/pendu"+ i+".png");
        this.dessin = new ImageView(this.lesImages.get(0));
        this.pg.setProgress(0F);
        this.motCrypte= new Text(this.modelePendu.getMotCrypte());
        this.motCrypte.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        vboxGauche.setAlignment(Pos.TOP_CENTER);
        vboxGauche.setPadding(new Insets(20));
        vboxGauche.getChildren().addAll(this.motCrypte,this.dessin,this.pg, this.clavier);
        
        VBox vboxDroite= new VBox(10);
        

        if(this.getNiveauPartie()==0){
            this.leNiveau.setText("Facile");}
        else if(this.getNiveauPartie()==1){
            this.leNiveau.setText("Moyen");}
        else if(this.getNiveauPartie()==2){
            this.leNiveau.setText("Diffcile");}
        else if(this.getNiveauPartie()==3){
            this.leNiveau.setText("Expert");}
        else{
            this.leNiveau.setText("Facile");
        }
       
        Label niveauPartie= new Label("Niveau "+this.leNiveau.getText());
        niveauPartie.setFont(Font.font("Arial", 20));

        vboxDroite.setAlignment(Pos.TOP_LEFT);
        vboxDroite.setPadding(new Insets(20));
        vboxDroite.getChildren().addAll(niveauPartie,this.leChrono(),nvMot);
        jeu.setLeft(vboxGauche);
        jeu.setRight(vboxDroite);

        return jeu;
    }
    public int getNiveauPartie(){
        RadioButton selection = (RadioButton) this.grpDifficulte.getSelectedToggle();
        if (selection ==null) return MotMystere.FACILE; // par défaut
        switch (selection.getText()) {
            case"Facile":return MotMystere.FACILE;
            case"Moyen":return MotMystere.MOYEN;
            case"Difficile": return MotMystere.DIFFICILE;
            case"Expert": return MotMystere.EXPERT;
            default: return MotMystere.FACILE;
    }
}
    

    // /**
     // * @return la fenêtre d'accueil sur laquelle on peut choisir les paramètres de jeu
     // */
    private Pane fenetreAccueil(){
        VBox centre= new VBox();
        if(this.niveaux.isEmpty()){
            this.niveaux.add("Facile");
            this.niveaux.add("Moyen");
            this.niveaux.add("Difficile");
            this.niveaux.add("Expert");
        }
        
        VBox vboxDifficulte= new VBox();

        for (String nv : this.niveaux ){
            RadioButton radiobouton= new RadioButton(nv);
            radiobouton.setToggleGroup(this.grpDifficulte);
            vboxDifficulte.getChildren().addAll(radiobouton);
        }

        TitledPane nvDifficulte= new TitledPane("Niveau de difficulté", vboxDifficulte);
        nvDifficulte.setCollapsible(false);
        centre.setPadding(new Insets(20));
        centre.getChildren().addAll(this.bJouer, nvDifficulte);

        return centre;
        
    }

    /**
     * charge les images à afficher en fonction des erreurs
     * @param repertoire répertoire où se trouvent les images
     */
    private void chargerImages(String repertoire){
        for (int i=0; i<this.modelePendu.getNbErreursMax()+1; i++){
            File file = new File(repertoire+"/pendu"+i+".png");
            System.out.println(file.toURI().toString());
            this.lesImages.add(new Image(file.toURI().toString()));
        }
    }

    public void modeAccueil(){
        this.boutonMaison.setDisable(false);
        this.panelCentral.setCenter(fenetreAccueil());

    }
    
    public void modeJeu(){
        this.panelCentral.setCenter(fenetreJeu());
    }
    
    public void modeParametres(){
        // A implémenter
    }

    /** lance une partie */
    public void lancePartie(){
        this.modelePendu.setMotATrouver();
        this.clavier.desactiveTouches(null);
        
        if (this.chrono != null) {
            this.chrono.stop();;
        }
        this.chrono = new Chronometre(this);
        this.chrono.start();

    this.modeJeu();

    }

    /**
     * raffraichit l'affichage selon les données du modèle
     */
    public void majAffichage(){
        // A implementer
    }

    /**
     * accesseur du chronomètre (pour les controleur du jeu)
     * @return le chronomètre du jeu
     */
    public Chronometre getChrono(){
        
        return this.chrono; 
    }
//cours 4
    public Alert popUpPartieEnCours(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"La partie est en cours!\n Etes-vous sûr de l'interrompre ?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Attention");
        alert.setHeaderText("Confirmation");
        
        return alert;
    }
        
    public Alert popUpReglesDuJeu(){
        // A implementer
        //revoir Regle 
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Règle Jeu du Pendu");
        alert.setHeaderText("Règle Jeu du Pendu");
        alert.setContentText("Vous devez trouver le mot caché\nchaque lettre à trouver correspond à un trait.\nSi la lettre séléctioné n'est pas dans le mot\nalors une partie du pendu se dessine.\nSi le dessin est complet alors vous avez perdu,\nvous pouver voir ou vous en êtes grâce à la bar de progression.");
        return alert;
    }
    
    public Alert popUpMessageGagne(){
        // A implementer
        //fait
        Alert alert = new Alert(Alert.AlertType.INFORMATION); 
        alert.setTitle("Jeu du Pendu");
        alert.setHeaderText("Vous avez gagné :)");
        alert.setContentText("Bravo ! Vous avez gagné !");
        return alert;
    }
    
    public Alert popUpMessagePerdu(){
        // A implementer
        //fait    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jeu du Pendu");
        alert.setHeaderText("Vous avez perdu :(");
        alert.setContentText("Vous avez perdu\nLe mot à trouver était "+ this.modelePendu.getMotATrouve());
        return alert;
    }

    /**
     * créer le graphe de scène et lance le jeu
     * @param stage la fenêtre principale
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("IUTEAM'S - La plateforme de jeux de l'IUTO");
        stage.setScene(this.laScene());
        this.modeAccueil();
        stage.show();
    }

    /**
     * Programme principal
     * @param args inutilisé
     */
    public static void main(String[] args) {
        launch(args);
    }    
}
