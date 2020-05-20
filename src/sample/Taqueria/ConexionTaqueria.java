package sample.Taqueria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionTaqueria {

        private static final String host = "127.0.0.1";
        private static final String user = "topicos20";
        private static final String pwd = "1234";
        private static final String db = "taqueria";
        public static Connection con;

        public static void CrearConexion(){
            try{
                Class.forName("org.mariadb.jdbc.Driver");
                //Estamos abriendo el socket hacia el SGBD
                con = DriverManager.getConnection("jdbc:mariadb://"+host+":3306/"+db,user,pwd);

            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
                e.toString();
            }
            catch (SQLException e){
                e.printStackTrace();
                e.toString();

            }
            catch (Exception e){
                e.printStackTrace();
                e.toString();
            }
        }
}
