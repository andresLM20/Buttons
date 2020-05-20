package sample.Taqueria;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class InfoVentana extends Stage {

    public Scene escenaInfo;
    public VBox VBoxInfo = new VBox();
    public Label LblInfo;

    public InfoVentana(){
        CrearGUI();
        this.setTitle("Taqueria 'Los helados'");
        this.setScene(escenaInfo);
        this.show();
    }

    private void CrearGUI() {
        Label LblInfo = new Label("Programa elaborado por:\nAndrés Morales Martínez.\n\nPara la materia de:\nTópicos Avanzados de Programación.");
        LblInfo.setTextAlignment(TextAlignment.CENTER);
        LblInfo.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        VBoxInfo.getChildren().add(LblInfo);
        VBoxInfo.setAlignment(Pos.CENTER);
        VBoxInfo.setPrefSize(100,100);
        escenaInfo = new Scene(VBoxInfo, 300, 300);
        escenaInfo.getStylesheets().add("sample/Estilos/TaqueriaPrincipal.css");
    }
}
