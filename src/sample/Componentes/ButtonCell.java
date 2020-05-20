package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.ProductosDAO;
import sample.Vistas.FrmProducto;

import java.util.Optional;

public class ButtonCell extends TableCell<ProductosDAO,String> {
    private Button btnCelda;
    private ProductosDAO objP;

    public ButtonCell(int opc){
        if(opc == 1) {
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                objP = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());

                TableView<ProductosDAO> tbvTemp;
                tbvTemp = ButtonCell.this.getTableView();
                objP = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                new FrmProducto(tbvTemp, objP);


            });
        }else {
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del sistema :)");
                alert.setHeaderText("Confirmando accion");
                alert.setContentText("Deseas eliminar el producto?");
                Optional<ButtonType> result = alert.showAndWait();
                if(result.get() == ButtonType.OK){
                    objP = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    objP.delProducto();
                    //Refrescar tabla
                    ButtonCell.this.getTableView().setItems(objP.selAllProducto());
                    ButtonCell.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
            setGraphic(btnCelda);
    }
}
