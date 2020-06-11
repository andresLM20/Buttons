package sample.Taqueria;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Taqueria.ConexionTaqueria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MesaDAO {
    private int idMesa;


    public int getIdMesa() {
        return idMesa;
    }
    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }


    private Connection con;
    public MesaDAO(){
        con = ConexionTaqueria.con;
    }

    public void insMesa(){
        String query = "insert into tbl_mesa"+"(nomCat)" +
                "values("+idMesa+")";

        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void updMesa(){
        String query = "update tbl_mesa set nomCat="+idMesa+"";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void delMesa(){
        String query = "delete from tbl_mesa where idCat="+idMesa;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<MesaDAO> selAllMesa(){
        ObservableList<MesaDAO> listaMesa = FXCollections.observableArrayList();
        MesaDAO objP = null;
        String query = "select * from tbl_mesa order by idMesa";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new MesaDAO();
                objP.setIdMesa(res.getInt("idMesa"));

                listaMesa.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaMesa;
    }
    public void getMesaById(){
        String query = "select * from tbl_mesa where IdMesa ="+idMesa;
        try{
            Statement stmt = ConexionTaqueria.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                idMesa = res.getInt("ideMsa");

            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return idMesa +"";
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

