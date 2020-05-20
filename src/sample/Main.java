package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.Buscaminas.Buscaminas;
import sample.Buscaminas2.Buscaminas2;
import sample.Modelos.Conexion;
import sample.Taqueria.TaqueriaPrincipal;
import sample.Vistas.*;

public class Main extends Application{

//    private Button btn1, btn2, btn3, btn4;
//    private HBox hbox;
//    private VBox vbox;
    MenuBar mnbProyecto;
    Menu menCompetencia1, menCompetencia2, menSalir;
    MenuItem mitPracticas, mitBye, mitPractica1, mitPractica2, mitPractica3, mitPractica4, mitTaqueria;
    Scene escena;
    BorderPane brpPrincipal;

    @Override
    public void start(Stage primaryStage) throws Exception{

        brpPrincipal = new BorderPane();
        mnbProyecto = new MenuBar();
        brpPrincipal.setTop(mnbProyecto);

        menCompetencia1 = new Menu("1er Competencia");
        menCompetencia2 = new Menu("2da Competencia");
        menSalir = new Menu("Salir");

        mitPractica1 = new MenuItem("Buscaminas");
        mitPractica1.setOnAction(event -> OpcionMenu(1));
        mitPractica2 = new MenuItem("Taquimecanografo");
        mitPractica2.setOnAction(event -> OpcionMenu(2));
        mitPractica3 = new MenuItem("Productos");
        mitPractica3.setOnAction(event -> OpcionMenu(3));
        mitPractica4 = new MenuItem("Pista Atletismo");
        mitPractica4.setOnAction(event -> OpcionMenu(4));
        mitTaqueria = new MenuItem("Taqueria");
        mitTaqueria.setOnAction(event -> OpcionMenu(5));

        mitBye = new MenuItem("Bye");
        mitBye.setOnAction(event -> OpcionMenu(20));

        menCompetencia1.getItems().addAll(mitPractica1, mitPractica2);
        menCompetencia2.getItems().addAll(mitPractica3, mitPractica4, mitTaqueria);
        menSalir.getItems().add(mitBye);

        //Cargamos los menus al menubar
        escena = new Scene(brpPrincipal, 700, 400);
        escena.getStylesheets().add("sample/Estilos/estilos-principal.css");
        mnbProyecto.getMenus().addAll(menCompetencia1,menCompetencia2, menSalir);

        //Creamos la conexion
        Conexion.CrearConexion();

//        final URL resource = getClass().getResource("tusa.wav");
//        final Media media = new Media(resource.toString());
//        final MediaPlayer mediaPlayer = new MediaPlayer(media);
//        mediaPlayer.play();

        //primaryStage.setMaximized(true);
        primaryStage.setTitle("Hello Tópicos");
        primaryStage.setScene(escena);
        primaryStage.show();

        /*try{
            AudioClip audio = new AudioClip(Paths.get("sample/Audio/tusa.wav").toUri().toString());
            audio.play();
            audio.setVolume(0.85);
        } catch(Exception e)
        {
            e.printStackTrace();
        }*/



        /*new Hilo("Sonic").start();
        new Hilo("Rubensin").start();
        new Hilo("El prisas").start();
        new Hilo("ElLimas").start();
        new Hilo("Hulk").start();*/

        /*btn1 = new Button("btn1");
        btn1.setPrefWidth(80);

        btn2 = new Button("btn2");
        btn2.setPrefWidth(80);

        btn3 = new Button("btn3");
        btn4 = new Button("btn4");

        hbox = new HBox();
        vbox = new VBox();

        vbox.setSpacing(5);
        hbox.setSpacing(5);

        //BorderPane pane = new BorderPane();
        Label lbl1 = new Label("Botones chidos");
        //pane.setTop(lbl1);

        vbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(lbl1, btn1, btn2, hbox);
        hbox.getChildren().addAll(btn3, btn4); */

        //RecursoCompartido objRec = new RecursoCompartido();
        //new ProductorThread((objRec)).start();
        //new ConsumidorThread((objRec)).start();

        //new ServidorSocket().iniciarServidor();
        //new ClienteSocket().ConectarCliente();
    }

    private void OpcionMenu(int i) {
        switch (i){
            case 1:
                new Buscaminas();
                break;
            case 2:
                new Taquimecanografo();
                break;
            case 3:
                new CRUDProductos();
                break;
            case 4:
                new PistaAtletismo();
                break;
            case 5:
                new TaqueriaPrincipal();
                break;
            case 20:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
