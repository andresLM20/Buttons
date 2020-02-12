package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Vistas.Buscaminas;

public class Main extends Application{

//    private Button btn1, btn2, btn3, btn4;
//    private HBox hbox;
//    private VBox vbox;
    MenuBar mnbProyecto;
    Menu menCompetencia1, menCompetencia2, menSalir;
    MenuItem mitPracticas, mitBye, mitPractica1;
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

        mitBye = new MenuItem("Bye");
        mitBye.setOnAction(event -> OpcionMenu(20));

        menCompetencia1.getItems().addAll(mitPractica1);
        menSalir.getItems().add(mitBye);

        //Cargamos los menus al menubar
        escena = new Scene(brpPrincipal, 700, 400);
        escena.getStylesheets().add("sample/Estilos/estilos-principal.css");
        mnbProyecto.getMenus().addAll(menCompetencia1,menCompetencia2, menSalir);

        //primaryStage.setMaximized(true);
        primaryStage.setTitle("Hello Tópicos");
        primaryStage.setScene(escena);
        primaryStage.show();

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
    }

    private void OpcionMenu(int i) {
        switch (i){
            case 1:
                new Buscaminas();
                break;
            case 20:
                System.exit(0);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
