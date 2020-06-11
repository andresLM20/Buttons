package sample.Taqueria;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Administrador extends Stage {
    private Scene escena;

    public Button btnEmpleados = new Button("Gestionar Empleados");
    public Button btnAlimentos = new Button("Gestionar Alimentos");
    public Button btnCat = new Button("Gestionar Categorias de Alimentos");
    public Button btnMesas = new Button("Gestionar Mesas");
    public Button btnVolver = new Button("Volver");

    public VBox VBoxPrincipal = new VBox();
    public VBox VBoxParent = new VBox();
    public VBox VBoxEmpleado = new VBox();
    public VBox VBoxAdmin = new VBox();

    public Administrador(){
        CrearGUI();
        this.setTitle("Taqueria 'El taco chinito'");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        StackPane ParentStack =new StackPane();
        ParentStack.setPadding(new Insets(10));

        ParentStack.getChildren().addAll(VBoxPrincipal);
        VBoxPrincipal.setVisible(true);
        VBoxEmpleado.setVisible(true);
        VBoxAdmin.setVisible(true);

        VBoxEmpleado.setAlignment(Pos.CENTER);
        VBoxAdmin.setAlignment(Pos.CENTER);

        //ConstruirVistaPrincipal();
        //ConstruirVistaEmpleado();
        //ConstruirVistaAdmin();

        escena = new Scene(ParentStack, 700, 500);
        escena.getStylesheets().add("sample/Estilos/TaqueriaPrincipal.css");

        //btnTaco.setOnAction(event -> Info());
        //btnScEmp.setOnAction(event -> MostrarVistaEmpleado());
        //btnScAdmin.setOnAction(event -> MostrarVistaAdmin());
    }
}
