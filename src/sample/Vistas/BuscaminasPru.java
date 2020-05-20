package sample.Vistas;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Eventos.EventoBuscaminas;

public class BuscaminasPru extends Stage {

    private Label lblNoRows, lblNoColumns;
    private TextField txtNoRows, txtNoColumns;
    private Button btnMinar;
    private GridPane gdpCampo;
    private Button[][] arBtnCeldas;
    private Scene escena;
    private HBox HBox;
    private VBox VBox;

    public BuscaminasPru(){
        CrearGUI();
        this.setTitle("Mi Buscaminas Chido :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        VBox = new VBox();
        lblNoRows = new Label("No Rows");
        lblNoColumns = new Label("No Columns");
        txtNoRows = new TextField();
        txtNoColumns = new TextField();

        btnMinar = new Button("Minar Campo");
        //btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
/*   4)    */btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventoBuscaminas(txtNoRows, txtNoColumns, arBtnCeldas, gdpCampo,VBox));
//        1) btnMinar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                System.out.println("Mi tercer evento");
//            }
//        });
//        2) btnMinar.setOnAction(event -> {System.out.println("Mi cuarto evento");});
//        3)  btnMinar.setOnAction(event -> Evento());

        HBox = new HBox();
        HBox.getChildren().addAll(lblNoRows, txtNoRows, lblNoColumns, txtNoColumns, btnMinar);
        VBox.getChildren().addAll(HBox);

        escena = new Scene(VBox, 500,350);
        escena.getStylesheets().add("sample/Estilos/estilo-buscaminas.css");

    }

    private void Evento() {
        System.out.println("Mi quinto evento");
    }


    public void handle(Event event) {
        System.out.println("Mi primer evento");
    }
}
