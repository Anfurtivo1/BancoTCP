/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancotcpservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Andres
 */
public class BancoTCPServidor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerSocket servidor;
        Socket cliente;
        int PUERTO = 5000;
        int opcion;
        int dinero;
        int cantidadDinero=2000;

        System.out.println("Servidor arrancado");

        try {
            servidor = new ServerSocket(PUERTO);
            System.out.println("Esperando...");
        do {
            cliente = servidor.accept();
            DataOutputStream escribir = new DataOutputStream(cliente.getOutputStream());
            DataInputStream leer = new DataInputStream(cliente.getInputStream());
            
           opcion=leer.readInt();
            
            switch (opcion) {
                case 1:
                    if (cantidadDinero>0) {
                        System.out.println("Se va a ejecutar la opcion 1");
                        escribir.writeUTF("Se ha elegido retirar dinero");
                        dinero=leer.readInt();
                        cantidadDinero=cantidadDinero-dinero;
                        escribir.writeUTF("Has elegido retirar "+dinero+" €, ahora hay "+cantidadDinero);
                    }
                    
                    break;
                case 2:
                    System.out.println("Se va a ejecutar la opcion 2");
                    escribir.writeUTF("Se ha elegido ingresar dinero");
                    dinero=leer.readInt();
                    cantidadDinero=cantidadDinero+dinero;
                    escribir.writeUTF("Has elegido ingresar "+dinero+" €, ahora hay "+cantidadDinero);
                    break;
                case 3:
                    System.out.println("Se va a ejecutar la opcion 3");
                    escribir.writeUTF("Se ha elegido comprobar dinero");
                    escribir.writeUTF("Tienes "+cantidadDinero+" €");
                    break;
                case 4:
                    System.out.println("Adios");
                    break;    
                default:
                    escribir.writeUTF("Esa no es una opción correcta");
            }
            
        } while (opcion!=4);
        cliente.close();
        }
            catch (Exception e)
        {
            System.out.println("Error en "+e);
        }
    }
    
}
