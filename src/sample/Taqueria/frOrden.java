package sample.Taqueria;

//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//import sample.Componentes.ButtonCell;
//import sample.Modelos.ProveedoresDAO;
//
//
//public class frOrden extends Stage {
//
//    private PedidosDAO objP;
//    private TableView <PedidosDAO> tbvPedidos;
//    private TextField txtDesc, txtCosto, txtPrecio, txtExistencia;
//    private ComboBox<String> cmbVigente;
//    private ComboBox<ProveedoresDAO> cmbProveedor;
//    private Button btnGuardar;
//    private Scene escena;
//    private VBox vbox;
//
//    public frOrden(TableView<PedidosDAO> tbvPedidos, PedidosDAO objP){
//        if(objP != null)
//            objP = objP;
//        else
//            objP = new PedidosDAO();
//
//        this.tbvPedidos = tbvPedidos;
//        CrearGUI();
//        this.setTitle("Gestion de productos");
//        this.setScene(escena);
//        this.show();
//    }
//
//    private void CrearGUI() {
//        vbox = new VBox();
//        txtDesc = new TextField();
//        txtDesc.setPromptText("Introduce la descripcion");
//        txtCosto = new TextField();
//        txtCosto.setPromptText("Introduce el costo");
//        txtPrecio = new TextField();
//        txtPrecio.setPromptText("Introduce el precio");
//        txtExistencia = new TextField();
//        txtExistencia.setPromptText("Introduce la existencia");
//
//        txtDesc.setText(objP.getNomProducto());
//        txtCosto.setText(objP.getCosto()+"");
//        txtPrecio.setText(objP.getPrecio()+"");
//        txtExistencia.setText(objP.getExistencia()+"");
//
//        cmbProveedor = new ComboBox();
//        cmbProveedor.setItems(new ProveedoresDAO().selAllProveedores());
//        ProveedoresDAO objPr = new ProveedoresDAO();
//        objPr.setIdProveedor(objP.getIdProveedor());
//        objPr.getProvById();
//        cmbProveedor.setValue(objPr);
//
//        cmbVigente = new ComboBox();
//        ObservableList<String> listVigente = FXCollections.observableArrayList();
//        listVigente.addAll("Vigente", "Descontinuado");
//        String val = (objP.isVigente()==true) ? "Vigente" : "Descontinuado";
//        cmbVigente.setItems(listVigente);
//        cmbVigente.setValue(val);
//
//        btnGuardar = new Button("Guardar");
//        btnGuardar.setOnAction(event -> guardarDatos());
//        vbox.getChildren().addAll(txtDesc, txtCosto, txtPrecio, txtExistencia, cmbVigente, cmbProveedor, btnGuardar);
//        escena = new Scene(vbox, 250,250);
//    }
//
//    private void guardarDatos() {
//
//        objP.setNomProducto(txtDesc.getText());
//        objP.setCosto(Float.parseFloat(txtCosto.getText()));
//        objP.setPrecio(Float.parseFloat(txtPrecio.getText()));
//        objP.setExistencia(Integer.parseInt(txtExistencia.getText()));
//
//        boolean ban = (cmbVigente.getValue() == "Vigente") ? true : false;
//        objP.setVigente(ban);
//
//        ProveedoresDAO objTemp = cmbProveedor.getValue();  //cmbProveedor.getItems().get(cmbProveedor.getSelectionModel().getSelectedIndex());
//        objP.setIdProveedor(objTemp.getIdProveedor());
//
//        if(objP.getIdProducto() >= 1)
//        {
//            objP.updProducto();
//        }else {
//            objP.insProducto();
//        }
//
//        tbvPedidos.setItems(objP.selAllPedido());
//        tbvPedidos.refresh();
//
//        this.close();
//
//    }
//}
