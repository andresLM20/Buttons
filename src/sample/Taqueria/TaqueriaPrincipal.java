package sample.Taqueria;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import sample.Taqueria.PedidosDAO;

import java.sql.Date;

public class TaqueriaPrincipal extends Stage {

    private Scene escena;

    public Button btnOrden = new Button("Ordenes");
    public Button btnGestPed = new Button("Gestionar Pedido");
    public Button btnFinPed = new Button("Finalizar Pedido/Ticket");
    public Button btnEmps = new Button("Empleados");
    public Button btnCatAli = new Button("Categorias de Alimentos");
    public Button btnAlim = new Button("Alimentos");
    public Button btnTaco = new Button();
    public Button btnScEmp = new Button("Empleados");
    public Button btnScAdmin = new Button("Administrador");
    public Button btnVolver = new Button("Volver");

    private TableView <PedidosDAO>tbvPedidos;
    private PedidosDAO objP;

    public VBox VBoxParent = new VBox();
    public VBox VBoxPrincipal = new VBox();
    public VBox VBoxEmpleado = new VBox();
    public VBox VBoxAdmin = new VBox();

    public Button btnIngresarAdmin;
    public TextField txtUsuario = new TextField();
    public TextField txtPass = new TextField();
    public Label lblTitle = new Label("PEDIDOS");

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
        txtUsuario.setPromptText("Ingrese usuario");
        txtUsuario.setPrefSize(120,50);
        txtPass.setPromptText("Ingrese contraseña");
        txtPass.setPrefSize(120,50);
        btnIngresarAdmin = new Button("Acceder");
        btnIngresarAdmin.setPrefSize(120,50);
        btnVolver = new Button("Regresar");
        btnVolver.setPrefSize(120,50);
        btnVolver.setOnAction(event -> {
            this.close();
            new TaqueriaPrincipal();
        });

        VBoxAdmin.getChildren().addAll(txtUsuario,txtPass,btnIngresarAdmin,btnVolver);
        btnIngresarAdmin.setOnAction(event -> {
            if(txtUsuario.getText().equals("admin")&&txtPass.getText().equals("admin")){
                this.close();
                new Administrador();
            }else{
                txtUsuario.setText("");
                txtPass.setText("");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("Datos incorrectos");
                alert.setContentText("Por favor, ingrese el usuario y contraseña correctos");
                alert.showAndWait();
            }
        });

    }

    private void ConstruirVistaEmpleado() {
        tbvPedidos = new TableView<>();
        objP = new PedidosDAO();

        btnOrden = new Button("Ordenes");
        btnOrden.setPrefSize(120,50);
//        btnOrden.setOnAction(event -> {
//            new frOrden(tbvPedidos,null);
//        });
        btnFinPed = new Button(("Pedido"));
        btnFinPed.setPrefSize(120,50);
        btnFinPed.setOnAction(event -> {
            new frmPedido(tbvPedidos,null);
        });

        btnVolver = new Button("Regresar");
        btnVolver.setPrefSize(120,50);
        btnVolver.setOnAction(event -> {
            close();
            new TaqueriaPrincipal();
        });
        lblTitle.setAlignment(Pos.CENTER_LEFT);
        VBox VboxLabel = new VBox();
        VboxLabel.getChildren().addAll(lblTitle);
        VboxLabel.setAlignment(Pos.CENTER_LEFT);
        lblTitle.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        VBoxEmpleado.getChildren().addAll(VboxLabel,tbvPedidos,btnFinPed,btnOrden, btnVolver);
        VBoxEmpleado.setAlignment(Pos.CENTER_RIGHT);
    }

    private void CrearTabla(){

        TableColumn<PedidosDAO,Integer> tbcIdPedido = new TableColumn<>("ID");
        tbcIdPedido.setCellValueFactory(new PropertyValueFactory<>("idPed"));

        TableColumn<PedidosDAO, Date> tbcfechaPed = new TableColumn<>("Fecha");
        tbcfechaPed.setCellValueFactory(new PropertyValueFactory<>("fechaPed"));

        TableColumn<PedidosDAO,Integer> tbcIdEmp = new TableColumn<>("Empleado");
        tbcIdEmp.setCellValueFactory(new PropertyValueFactory<>("nomEmp"));

        TableColumn<PedidosDAO,Integer> tbcIdMesa = new TableColumn<>("Mesa");
        tbcIdMesa.setCellValueFactory(new PropertyValueFactory<>("idMesa"));

        TableColumn<PedidosDAO,String> tbcnomAli = new TableColumn<>("Alimento");
        tbcnomAli.setCellValueFactory(new PropertyValueFactory<>("nombreAli"));

         tbvPedidos.getColumns().addAll(tbcIdPedido, tbcfechaPed, tbcIdEmp,tbcIdMesa, tbcnomAli);
         tbvPedidos.setItems(objP.selAllPedido());
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
        VBoxEmpleado.setVisible(false);
        VBoxAdmin.setVisible(false);
        btnScAdmin.setId("btnPTaco");
        btnScEmp.setId(("btnPTaco"));
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
        CrearTabla();
    }

    private void Info() {
        new InfoVentana();
    }
}

