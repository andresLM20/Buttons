package sample.Taqueria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Taqueria.ConexionTaqueria;
import sample.Modelos.ProductosDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AlimentosDAO {
    private int idAlimento;
    private String nombreAli;
    private String descAli;
    private int precioAli;
    private int idCat;

    public int getIdAlimento() {
        return idAlimento;
    }
    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }
    public String getNombreAli() {
        return nombreAli;
    }
    public void setNombreAli(String nombreAli) {
        this.nombreAli = nombreAli;
    }
    public String getDescAli() {
        return descAli;
    }
    public void setDescAli(String descAli) {
        this.descAli = descAli;
    }
    public int getPrecioAli() {
        return precioAli;
    }
    public void setPrecioAli(int precioAli) {
        this.precioAli = precioAli;
    }
    public int getIdCat() {
        return idCat;
    }
    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    private Connection con;
    public AlimentosDAO(){
        con = ConexionTaqueria.con;
    }

    public void insAlimento(){
        String query = "insert into tbl_alimentos "+"(nombreAli, descAli, precioAli, idCat)" +
                "values('"+nombreAli+"','"+descAli+"',"+precioAli+","+idCat+")";

        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void updAlimento(){
        String query = "update tbl_alimentos set nombreAli='"+nombreAli+"',descAli='"+descAli+"',precioAli="+precioAli+",idCat="
                +idCat+")";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void delAlimentos(){
        String query = "delete from tbl_alimentos where idProducto="+idAlimento;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<AlimentosDAO> selAllAlimentos(){
        ObservableList<AlimentosDAO> listaAlimentos = FXCollections.observableArrayList();
        AlimentosDAO objP = null;
        String query = "select * from tbl_alimentos order by nombreAli";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new AlimentosDAO();
                objP.setIdAlimento(res.getInt("idAlimento"));
                objP.setNombreAli(res.getString("nombreAli"));
                objP.setDescAli(res.getString("descAli"));
                objP.setPrecioAli(res.getInt("precioAli"));
                objP.setIdCat(res.getInt("idCat"));

                listaAlimentos.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaAlimentos;
    }
    public void getAliById(){
        String query = "select * from tbl_alimentos where IdAlimento ="+idAlimento;
        try{
            Statement stmt = ConexionTaqueria.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                nombreAli = res.getString("nombreAli");
                descAli = res.getString("descAli");
                precioAli = res.getInt("precioAli");
                idCat = res.getInt("idCat");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return nombreAli + "-"+"$"+precioAli;
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
