package sample.Vistas;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClienteSocket {

    private InetAddress direccionServidor;
    private Socket cliente;
    private byte[] ip = new byte[]{(byte)192,(byte)168,(byte)100,(byte)6};  //Direccion del servidor

    public void ConectarCliente(){
        try{
            direccionServidor = InetAddress.getByAddress(ip);
            cliente = new Socket(direccionServidor, 5000);

            //Leemos el flujo de entrada hacia el cliente
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            System.out.println(entrada.readLine());

            //Escribimos en el flujo de salida hacia el servidor
            PrintStream salida = new PrintStream(cliente.getOutputStream());
            salida.println("Mensaje enviado desde el cliente socket");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
