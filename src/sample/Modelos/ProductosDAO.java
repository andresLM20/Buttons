package sample.Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductosDAO {
    private int idProducto;
    private String nomProducto;
    private float costo;
    private float precio;
    private int existencia;
    private boolean vigente;
    private int idProveedor;

    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNomProducto() {
        return nomProducto;
    }
    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }
    public float getCosto() {
        return costo;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public int getExistencia() {
        return existencia;
    }
    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }
    public boolean isVigente() {
        return vigente;
    }
    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
    public int getIdProveedor() {
        return idProveedor;
    }
    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    private Connection con;
    public ProductosDAO(){
        con = Conexion.con;
    }

    public void insProducto(){
        String query = "insert into tbl_productos "+"(nomProducto,precioProducto,costoProducto,exisProducto,vigenciaProducto,idProveedor)" +
                "values('"+nomProducto+"',"+precio+","+costo+","+existencia+","+vigente+","+idProveedor+")";

        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void updProducto(){
        String query = "update tbl_productos set nomProducto='"+nomProducto+"',precioProducto="+precio+",costoProducto="+costo+",exisProducto="
                +existencia+",vigenciaProducto="+vigente+",idProveedor="+idProveedor+" where idProducto="+idProducto;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void delProducto(){
        String query = "delete from tbl_productos where idProducto="+idProducto;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<ProductosDAO> selAllProducto(){
        ObservableList<ProductosDAO> listaProductos = FXCollections.observableArrayList();
        ProductosDAO objP = null;
        String query = "select * from tbl_productos order by nomProducto";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new ProductosDAO();
                objP.setIdProducto(res.getInt("idProducto"));
                objP.setNomProducto(res.getString("nomProducto"));
                objP.setPrecio(res.getFloat("precioProducto"));
                objP.setCosto(res.getFloat("costoProducto"));
                objP.setExistencia(res.getInt("exisProducto"));
                objP.setVigente(res.getBoolean("vigenciaProducto"));
                objP.setIdProveedor(res.getInt("idProveedor"));

                listaProductos.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaProductos;
    }



    /*public ObservableList<ProductosDAO> selByIdProducto(){
        ObservableList<ProductosDAO> listaProductos = FXCollections.observableArrayList();
        ProductosDAO objP = null;
        String query = "select * from tbl_productos";
        try{
            Statement stmt = con.createStatement();
            stmt.executeQuery(query);
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaProductos;
    }*/
}
