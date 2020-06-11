package sample.Taqueria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Modelos.Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriaDAO {
    private int idCat;
    private String nomCat;


    public int getIdCat() {
        return idCat;
    }
    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }
    public String getNomCat() {
        return nomCat;
    }
    public void setnomCat(String nomCat) {
        this.nomCat = nomCat;
    }


    private Connection con;
    public CategoriaDAO(){
        con = ConexionTaqueria.con;
    }

    public void insCat(){
        String query = "insert into tbl_categoriaAli"+"(nomCat)" +
                "values('"+nomCat+"')";

        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void updCat(){
        String query = "update tbl_categoriaAli set nomCat='"+nomCat+"'";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void delCat(){
        String query = "delete from tbl_categoriaAli where idCat="+idCat;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriaDAO> selAllCat(){
        ObservableList<CategoriaDAO> listaCat = FXCollections.observableArrayList();
        CategoriaDAO objP = null;
        String query = "select * from tbl_categoriaAli order by nomCat";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new CategoriaDAO();
                objP.setIdCat(res.getInt("idCat"));
                objP.setnomCat(res.getString("nomCat"));

                listaCat.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaCat;
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

