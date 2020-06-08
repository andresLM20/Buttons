package sample.Buscaminas;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Buscaminas extends Stage {
    public Label lblNoRows, lblNoColumns;
    public TextField txtNoRows, txtNoColumns;
    public Button btnMinar, btnReiniciar;
    public Scene scene;

    public Buscaminas() {
        CrearGUI();
        this.setTitle("MI BUSCAMINAS");
        this.setFullScreen(false);
        this.setScene(scene);
        this.show();
    }

    private void CrearGUI() {
        VBox VBox = new VBox();
        HBox HBox = new HBox();
        HBox HBoxGrid = new HBox();
        scene = new Scene(VBox,550,550);
        scene.getStylesheets().add("sample/Estilos/estilo-buscaminas.css");

        lblNoRows = new Label("No. Rows");
        lblNoColumns = new Label("No. Columns");
        txtNoRows = new TextField();
        txtNoColumns = new TextField();
        btnMinar = new Button("Minar Campo");
        btnReiniciar = new Button("Reiniciar Juego");
        HBox.getChildren().addAll(lblNoRows, txtNoRows, lblNoColumns, txtNoColumns, btnMinar, btnReiniciar);
        VBox.getChildren().addAll(HBox,HBoxGrid);

        btnReiniciar.setOnAction(event -> {
            close();
            new Buscaminas();
        });

        btnMinar.setOnAction(event -> {
            btnMinar.setDisable(false);
            int nr = Integer.parseInt(this.txtNoRows.getText());
            int nc = Integer.parseInt(this.txtNoColumns.getText());
            if(HBoxGrid.getChildren().isEmpty()){
                HBoxGrid.setAlignment(Pos.CENTER);
                HBoxGrid.getChildren().add(new Grid(nc,nr));
            }else{
                HBoxGrid.getChildren().clear();
                HBoxGrid.setAlignment(Pos.CENTER);
                HBoxGrid.getChildren().add(new Grid(nc,nr));
            }
        });
    }
}
