package sample.Eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.stage.WindowEvent;

public class EventoVentanaTaqui implements EventHandler {

    @Override
    public void handle(Event event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Mensaje del sistema");
        alert.setHeaderText("Amonos....");
        alert.setContentText("Que tengan una excelente noche");
        //alert.showAndWait();
    }
}
