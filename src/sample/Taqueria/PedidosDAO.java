package sample.Taqueria;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class PedidosDAO {
    private int idPed;
    private String fechaPed;
    private int idEmp;
    private int idMesa;

    public String getNombreAli() {
        return nombreAli;
    }

    public void setNombreAli(String nombreAli) {
        this.nombreAli = nombreAli;
    }

    public String getNomEmp() {
        return nomEmp;
    }

    public void setNomEmp(String nomEmp) {
        this.nomEmp = nomEmp;
    }

    private String nombreAli;
    private String nomEmp;

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }

    public String getFechaPed() {
        return fechaPed;
    }

    public void setFechaPed(String fechaPed) {
        this.fechaPed = fechaPed;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }


    private Connection con;

    public PedidosDAO() {
        con = ConexionTaqueria.con;
    }

    public void insPedido() {
        String query = "insert into tbl_pedido " + "( fechaPed, idEmp, idMesa)" +
                "values('" + fechaPed + "'," + idEmp + "," + idMesa + ")";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void updPedido() {
        String query = "update tbl_pedido set fechaPed='" + fechaPed + "',idEmp=" + idEmp + ",idMesa=" + idMesa + ")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delPedido() {
        String query = "delete from tbl_pedido where idPed=" + idPed;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<PedidosDAO> selAllPedido() {
        ObservableList<PedidosDAO> listaPedidos = FXCollections.observableArrayList();
        PedidosDAO objP = null;
        String query = "select p.idPed,fechaPed,nomEmp,idMesa,nombreAli from ((tbl_pedido p join tbl_Tiene t on p.idPed = t.idPed) join tbl_alimentos a on t.idAlimento = a.idAlimento) join tbl_empleado e on p.idEmp = e.idEmp";
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objP = new PedidosDAO();
                objP.setIdPed(res.getInt("idPed"));
                objP.setFechaPed(res.getString("fechaPed"));
                objP.setNomEmp(res.getString("nomEmp"));
                objP.setIdMesa(res.getInt("idMesa"));
                objP.setNombreAli(res.getString("nombreAli"));

                listaPedidos.add(objP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPedidos;
    }
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

