package sample.Taqueria;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Modelos.Conexion;

import javax.rmi.CORBA.Tie;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TieneDAO {
    private int idPed;
    private int idAlimento;


    public int getIdCat() {
        return idPed;
    }
    public void setIdPed(int idCat) {
        this.idPed = idPed;
    }
    public int getIdAlimento() {
        return idAlimento;
    }
    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }


    private Connection con;
    public TieneDAO(){
        con = ConexionTaqueria.con;
    }

    public void insTiene(){
        String query = "insert into tbl_Tiene"+"(idPed,idAlimento)" +
                "values("+idPed+","+idAlimento+")";

        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void updTiene(){
        String query = "update tbl_Tiene set idPed="+idPed+",idAlimento="+idAlimento;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void delTiene(){
        String query = "delete from tbl_Tiene where idPed="+idPed+"& idAlimento="+idAlimento;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<TieneDAO> selAllPed(){
        ObservableList<TieneDAO> listaPed = FXCollections.observableArrayList();
        TieneDAO objP = null;
        String query = "select * from tbl_Tiene";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new TieneDAO();
                objP.setIdPed(res.getInt("idPed"));
                objP.setIdAlimento(res.getInt("nomCat"));

                listaPed.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaPed;
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

