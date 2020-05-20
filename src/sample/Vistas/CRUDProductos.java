package sample.Vistas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.Componentes.ButtonCell;
import sample.Modelos.ProductosDAO;

public class CRUDProductos extends Stage {

    private Scene escena;
    private VBox VBox;
    private TableView <ProductosDAO> tblVwProductos;
    private Button btnAgregar;
    private ProductosDAO objP;

    public CRUDProductos(){
        objP = new ProductosDAO();
        CrearGUI();
        this.setTitle("CRUD Productos");
        this.setScene(escena);
        this.show();
    }

    private void CrearGUI() {
        VBox = new VBox();
        tblVwProductos = new TableView<>();
        CrearTabla();
        btnAgregar = new Button("Agregar Producto");
        btnAgregar.setOnAction(event -> AgregarProducto());
        VBox.getChildren().addAll(tblVwProductos,btnAgregar);
        escena = new Scene(VBox, 1000, 300);
    }

    private void CrearTabla() {
        try{
        TableColumn<ProductosDAO, Integer> tbcIdProducto = new TableColumn<>("ID");
        tbcIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<ProductosDAO, String> tbcNomProducto = new TableColumn<>("DESCRIPCIÓN");
        tbcNomProducto.setCellValueFactory(new PropertyValueFactory<>("nomProducto"));

        TableColumn<ProductosDAO, Float> tbcPrecioProducto = new TableColumn<>("PRECIO");
        tbcPrecioProducto.setCellValueFactory(new PropertyValueFactory<>("precioProducto"));

        TableColumn<ProductosDAO, Float> tbcCostoProducto = new TableColumn<>("COSTO");
        tbcCostoProducto.setCellValueFactory(new PropertyValueFactory<>("costoProducto"));

        TableColumn<ProductosDAO, Integer> tbcExisProducto = new TableColumn<>("EXISTENCIAS");
        tbcExisProducto.setCellValueFactory(new PropertyValueFactory<>("exisProducto"));

        TableColumn<ProductosDAO, Boolean> tbcVigenciaProducto = new TableColumn<>("VIGENCIA");
        tbcVigenciaProducto.setCellValueFactory(new PropertyValueFactory<>("vigenciaProducto"));

        TableColumn<ProductosDAO, Integer> tbcProveedorProducto = new TableColumn<>("ID DEL PROVEEDOR");
        tbcProveedorProducto.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));

        /*************************************************************************************************/

        TableColumn<ProductosDAO, String> tbcBorrar = new TableColumn<>("Borrar");
        tbcBorrar.setCellFactory(
                new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
                    @Override
                    public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> param) {
                        return new ButtonCell(2);
                    }
                }
        );
        TableColumn<ProductosDAO, String> tbcEditar = new TableColumn<>("Editar");
        tbcEditar.setCellFactory(
                new Callback<TableColumn<ProductosDAO, String>, TableCell<ProductosDAO, String>>() {
                    @Override
                    public TableCell<ProductosDAO, String> call(TableColumn<ProductosDAO, String> param) {
                        return new ButtonCell(1);
                    }
                }
        );

        tblVwProductos.getColumns().addAll(tbcIdProducto,tbcNomProducto,tbcPrecioProducto,tbcCostoProducto,tbcExisProducto,tbcVigenciaProducto,tbcProveedorProducto,tbcEditar,tbcBorrar);
        tblVwProductos.setItems(objP.selAllProducto());
    }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void AgregarProducto() {
        new FrmProducto(tblVwProductos,null);

    }
}
