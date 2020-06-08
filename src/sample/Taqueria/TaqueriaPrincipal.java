package sample.Taqueria;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import sample.Modelos.ProductosDAO;

public class TaqueriaPrincipal extends Stage {

    private Scene escena;

    public Button btnOrden = new Button("Ordenes");
    public Button btnGestPed = new Button("Gestionar Pedido");
    public Button btnFinPed = new Button("Finalizar Pedido/Ticket");
    public Button btnEmps = new Button("Empleados");
    public Button btnClientes = new Button("Clientes");
    public Button btnCatAli = new Button("Categorias de Alimentos");
    public Button btnAlim = new Button("Alimentos");
    public Button btnTaco = new Button();
    public Button btnScEmp = new Button("Empleados");
    public Button btnScAdmin = new Button("Administrador");
    public Button btnVolver = new Button("Volver");

    public VBox VBoxParent = new VBox();
    public VBox VBoxPrincipal = new VBox();
    public VBox VBoxEmpleado = new VBox();
    public VBox VBoxAdmin = new VBox();

    public HBox HBoxP1 = new HBox();
    public VBox VBoxP1 = new VBox();


    public TaqueriaPrincipal(){
        CrearGUI();
        this.setTitle("Taqueria 'El taco chinito'");
        this.setScene(escena);
        this.show();
    }

    public void CrearGUI() {
        StackPane ParentStack =new StackPane();
        ParentStack.setPadding(new Insets(10));

        ParentStack.getChildren().addAll(VBoxEmpleado,VBoxAdmin,VBoxPrincipal);
        VBoxPrincipal.setVisible(true);
        VBoxEmpleado.setVisible(true);
        VBoxAdmin.setVisible(true);

        VBoxEmpleado.setAlignment(Pos.CENTER);
        VBoxAdmin.setAlignment(Pos.CENTER);

        ConstruirVistaPrincipal();
        ConstruirVistaEmpleado();
        ConstruirVistaAdmin();

        escena = new Scene(ParentStack, 700, 500);
        escena.getStylesheets().add("sample/Estilos/TaqueriaPrincipal.css");

        btnTaco.setOnAction(event -> Info());
        btnScEmp.setOnAction(event -> MostrarVistaEmpleado());
        btnScAdmin.setOnAction(event -> MostrarVistaAdmin());
    }

    private void ConstruirVistaAdmin() {
        Label MostrarAdmin = new Label("Esta es la \nvista Admin");
        VBoxAdmin.getChildren().addAll(MostrarAdmin);
    }

    private void ConstruirVistaEmpleado() {
        btnOrden = new Button("Ordenes");
        btnOrden.setPrefSize(120,50);
        btnGestPed = new Button("Gestionar pedido");
        btnGestPed.setPrefSize(120,50);
        btnFinPed = new Button("Finalizar pedido");
        btnFinPed.setPrefSize(120,50);
        btnVolver = new Button("Regresar");
        btnVolver.setPrefSize(120,50);
        VBoxEmpleado.getChildren().addAll(btnOrden, btnGestPed, btnFinPed, btnVolver);
    }

    private void ConstruirVistaPrincipal() {
        ImageView tacoImg = new ImageView("sample/Imagenes/taco2.png");
        tacoImg.setFitHeight(200);
        tacoImg.setFitWidth(200);
        btnTaco.setGraphic(tacoImg);
        VBoxP1.getChildren().addAll(btnScEmp,btnScAdmin);
        btnTaco.setPrefSize(200,200);
        btnScEmp.setPrefSize(200,105);
        btnScAdmin.setPrefSize(200,105);
        HBoxP1.getChildren().addAll(btnTaco,VBoxP1);
        VBoxPrincipal.getChildren().addAll(HBoxP1);
        VBoxPrincipal.setAlignment(Pos.CENTER);
        HBoxP1.setAlignment(Pos.CENTER_RIGHT);
        btnTaco.setId("ButtonTaco");
    }

    private void MostrarVistaAdmin() {
        VBoxPrincipal.setVisible(false);
        VBoxEmpleado.setVisible(false);
        VBoxAdmin.setVisible(true);
    }

    private void MostrarVistaEmpleado() {
        VBoxPrincipal.setVisible(false);
        VBoxEmpleado.setVisible(true);
        VBoxAdmin.setVisible(false);
    }

    private void Info() {
        new InfoVentana();
    }
}

