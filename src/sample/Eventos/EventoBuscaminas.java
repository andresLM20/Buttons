package sample.Eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class EventoBuscaminas implements EventHandler {
    private TextField txtNoRows, txtNoColumns;
    private GridPane gdpCampo;
    private Button [][] arBtnCeldas;
    private VBox VBox;

    public EventoBuscaminas(TextField txtNR, TextField txtNC, Button[][] arBtnCeldas, GridPane gdpCampo, VBox VBox){
        this.txtNoRows = txtNR;
        this.txtNoColumns = txtNC;
        this.arBtnCeldas = arBtnCeldas;
        this.gdpCampo = gdpCampo;
        this.VBox = VBox;
    }

    @Override
    public void handle(Event event) {

        int nr = Integer.parseInt(txtNoRows.getText());
        int nc = Integer.parseInt(txtNoColumns.getText());

        if(arBtnCeldas != null)
            VBox.getChildren().remove(gdpCampo);

        System.out.println(nr + "   " + nc);
        arBtnCeldas = new Button[nr][nc];
        gdpCampo = new GridPane();
        gdpCampo.setPadding(new Insets(15));

        for(int i = 0; i < nr; i++)
        {
            for (int j = 0; j < nc; j++)
            {
                arBtnCeldas[i][j] = new Button(i+"-"+j);
                arBtnCeldas[i][j].setPrefSize(50,50);
                arBtnCeldas[i][j].setStyle("-fx-base: #303f9f; -fx-background-radius: 50;");
                gdpCampo.add(arBtnCeldas[i][j], j,i);
            }
        }
        VBox.getChildren().add(gdpCampo);
    }
}
