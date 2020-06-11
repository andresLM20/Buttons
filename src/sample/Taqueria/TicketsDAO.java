package sample.Taqueria;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Modelos.Conexion;
import sample.Modelos.ProductosDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;

public class TicketsDAO {
    private int idTicket;
    private Date fechaTicket;
    private int importe;
    private int idPed;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Date getFechaTicket() {
        return fechaTicket;
    }

    public void setFechaTicket(Date fechaTicket) {
        this.fechaTicket = fechaTicket;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public int getIdPed() {
        return idPed;
    }

    public void setIdPed(int idPed) {
        this.idPed = idPed;
    }


    private Connection con;

    public TicketsDAO() {
        con = ConexionTaqueria.con;
    }

    public void insTickets() {
        String query = "insert into tbl_ticket " + "(fechaTicket, importe, idPed)" +
                "values('" + fechaTicket + "'," + importe + "," + idPed + ")";

        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void updTicket() {
        String query = "update tbl_ticket set fechaTicket='" + fechaTicket + "',importe=" + importe + ",idPed=" + idPed + ")";
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void delTicket() {
        String query = "delete from tbl_ticket where idTicket=" + idTicket;
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<TicketsDAO> selAllTicket() {
        ObservableList<TicketsDAO> listaTickets = FXCollections.observableArrayList();
        TicketsDAO objP = null;
        String query = "select * from tbl_tickets order by idTicket";
        try {
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                objP = new TicketsDAO();
                objP.setIdTicket(res.getInt("idTicket"));
                objP.setFechaTicket(res.getDate("fechaTicket"));
                objP.setImporte(res.getInt("importe"));
                objP.setIdPed(res.getInt("idPed"));

                listaTickets.add(objP);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaTickets;
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
