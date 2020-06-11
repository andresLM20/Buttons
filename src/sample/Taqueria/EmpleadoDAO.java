package sample.Taqueria;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Taqueria.ConexionTaqueria;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadoDAO {
    private int idEmp;
    private String nomEmp;
    private String apPatEmp;
    private String apMatEmp;

    public int getIdEmp() {
        return idEmp;
    }
    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }
    public String getNomEmp() {
        return nomEmp;
    }
    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }
    public String getApPatEmp() {
        return apPatEmp;
    }
    public void setApPatEmp(String apPatEmp) {
        this.apPatEmp = apPatEmp;
    }
    public String getApMatEmp() {
        return apMatEmp;
    }
    public void setApMatEmp(String apMatEmp) {
        this.apMatEmp = apMatEmp;
    }



    private Connection con;
    public EmpleadoDAO(){
        con = ConexionTaqueria.con;
    }

    public void insEmpleado(){
        String query = "insert into tbl_empleado "+"(nomEmp, apPatEmp, apMatEmp)" +
                "values('"+nomEmp+"','"+apPatEmp+"','"+apMatEmp+"')";

        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void updEmpleado(){
        String query = "update tbl_empleados set nomEmp='"+nomEmp+"',apPatEmp='"+apPatEmp+"',apMatEmp='"+apMatEmp+"')";
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void delEmpleado(){
        String query = "delete from tbl_empleados where idEmp="+idEmp;
        try{
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ObservableList<EmpleadoDAO> selAllEmpleado(){
        ObservableList<EmpleadoDAO> listaEmpleados = FXCollections.observableArrayList();
        EmpleadoDAO objP = null;
        String query = "select * from tbl_empleado order by nomEmp";
        try{
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while(res.next()){
                objP = new EmpleadoDAO();
                objP.setIdEmp(res.getInt("idEmp"));
                objP.setNomEmp(res.getString("nomEmp"));
                objP.setApPatEmp(res.getString("apPatEmp"));
                objP.setApMatEmp(res.getString("apMatEmp"));

                listaEmpleados.add(objP);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaEmpleados;
    }

    public void getEmpleadoById(){
        String query = "select * from tbl_empleado where idEmp ="+idEmp;
        try{
            Statement stmt = ConexionTaqueria.con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            if(res.next()){
                nomEmp = res.getString("nomEmp");
                apPatEmp = res.getString("apPatEmp");
                apMatEmp = res.getString("apMatEmp");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {

        return nomEmp + " " + apPatEmp;
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