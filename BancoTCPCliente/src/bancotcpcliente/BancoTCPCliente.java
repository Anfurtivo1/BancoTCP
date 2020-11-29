/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancotcpcliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Andres
 */
public class BancoTCPCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner (System.in);
        InetAddress direccion;
        Socket servidor;
        int opcion;
        int cantidadDinero;
        int PUERTO = 5000;

        try {
            do {
                System.out.println("Indica una opcion");
                direccion = InetAddress.getLocalHost(); // dirección local
                servidor = new Socket(direccion, PUERTO);//Petición para el accept
                DataInputStream leer = new DataInputStream(servidor.getInputStream());
                DataOutputStream escribir = new DataOutputStream(servidor.getOutputStream());
                opcion=teclado.nextInt();
                escribir.writeInt(opcion);
                String mensaje=leer.readUTF();
                System.out.println(mensaje);
                
            switch (opcion) {
                case 1:
                    System.out.println("Indica la cantidad de dinero que quieres retirar");
                    cantidadDinero=teclado.nextInt();
                    escribir.writeInt(cantidadDinero);
                    mensaje=leer.readUTF();
                    System.out.println(mensaje);
                    break;
                case 2:
                    System.out.println("Indica la cantidad de dinero a ingresar");
                    cantidadDinero=teclado.nextInt();
                    escribir.writeInt(cantidadDinero);
                    mensaje=leer.readUTF();
                    System.out.println(mensaje);
                    
                    break;
                case 3:
                    mensaje=leer.readUTF();
                    System.out.println(mensaje);
                    break;
                case 4:
                    System.out.println("Adios");
                    break;    
                default:
                    System.out.println("Esa no es una opción correcta");
            }
            
            } while (opcion!=4);
            

            servidor.close();
        }
        catch (Exception e)
        {
            System.out.println("Error en "+e);
        }
    }
    
}
