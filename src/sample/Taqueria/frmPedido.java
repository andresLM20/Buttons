package sample.Taqueria;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class frmPedido extends Stage {

    private PedidosDAO objPed;
    private TableView <PedidosDAO> tbvPedidos;
    private DatePicker fechaPed;
    private ComboBox<EmpleadoDAO> cmbEmpleado;
    private ComboBox<MesaDAO> cmbMesa;
    private Button btnGuardar;
    private Scene escena;
    private VBox vbox;

    public frmPedido(TableView<PedidosDAO> tbvPedidos, PedidosDAO objPed){
        if(objPed != null)
           this.objPed = objPed;
        else
            this.objPed = new PedidosDAO();

        this.tbvPedidos = tbvPedidos;
        CrearGUI();
        this.setTitle("Crear pedido");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        vbox = new VBox();

         fechaPed = new DatePicker();

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
           DateTimeFormatter dateFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        fechaPed.setConverter(converter);

        cmbEmpleado = new ComboBox();
        cmbEmpleado.setItems(new EmpleadoDAO().selAllEmpleado());
        EmpleadoDAO objEmp = new EmpleadoDAO();
        objEmp.setIdEmp(objPed.getIdEmp());
        objEmp.getEmpleadoById();
        cmbEmpleado.setValue(objEmp);

        cmbMesa = new ComboBox();
        cmbMesa.setItems(new MesaDAO().selAllMesa());
        MesaDAO objMesa = new MesaDAO();
        objMesa.setIdMesa(objPed.getIdMesa());
        objMesa.getMesaById();
        cmbMesa.setValue(objMesa);

        btnGuardar = new Button("Guardar");
        btnGuardar.setOnAction(event -> guardarDatos());
        vbox.getChildren().addAll(fechaPed,cmbEmpleado,cmbMesa, btnGuardar);
        escena = new Scene(vbox, 250,250);

    }

    private void guardarDatos() {


        EmpleadoDAO objTemp = cmbEmpleado.getValue();  //cmbProveedor.getItems().get(cmbProveedor.getSelectionModel().getSelectedIndex());
        objPed.setIdEmp(objTemp.getIdEmp());

        MesaDAO objTemp2 = cmbMesa.getValue();
        objPed.setIdMesa(objTemp2.getIdMesa());

        objPed.setFechaPed(fechaPed.getValue()+"");

        if(objPed.getIdPed() >= 1)
        {
            objPed.updPedido();
        }else {
            objPed.insPedido();
        }

        tbvPedidos.setItems(objPed.selAllPedido());
        tbvPedidos.refresh();

        this.close();

    }
}