package sample.Buscaminas;

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
    public Button btnMinar;
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
        scene = new Scene(VBox,800,500);

        lblNoRows = new Label("No. Rows");
        lblNoColumns = new Label("No. Columns");
        txtNoRows = new TextField();
        txtNoColumns = new TextField();
        btnMinar = new Button("Minar Campo");
        HBox.getChildren().addAll(lblNoRows, txtNoRows, lblNoColumns, txtNoColumns, btnMinar);
        VBox.getChildren().addAll(HBox,HBoxGrid);
        btnMinar.setOnAction(event -> {
            btnMinar.setDisable(false);
            int nr = Integer.parseInt(this.txtNoRows.getText());
            int nc = Integer.parseInt(this.txtNoColumns.getText());
            if(HBoxGrid.getChildren().isEmpty()){
                HBoxGrid.getChildren().add(new Grid(nc,nr));
            }else{
                HBoxGrid.getChildren().clear();
                HBoxGrid.getChildren().add(new Grid(nc,nr));
            }
        });
    }
}
