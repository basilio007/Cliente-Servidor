package clases;
import java.io.*;
import java.net.*;

public class ClienteMain {
    public static void main(String[] args) {

    String HOST = "localhost";
    int PUERTO = 1234;

    // declaration section:
    // clientSocket: el socket de nuestro cliente 
    // os: output stream
    // is: input stream

        Socket clienteSocket = null;  
        DataOutputStream os = null;
        BufferedReader is = null;

    // Initialization section:
    // tratar de abrir un socket en el purto dado
   

        try {
        	clienteSocket = new Socket(HOST, PUERTO);
            os = new DataOutputStream(clienteSocket.getOutputStream());
            is = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("host desconocido: " + HOST);
        } catch (IOException e) {
            System.err.println("No se pudo obtener el I/O para la coneccion a: " + HOST);
        }

    //si todo se ha inicializado entoces podremos ecribir algun dato
    

    if (clienteSocket == null || os == null || is == null) {
        System.err.println( "Algo está mal. una variable es nula." );
        return;
    }

    try {
        while ( true ) {
        	
        System.out.print( "introduzca un mensage (escriba (parar) para parar la conección, y (salir) para salir del servidor");
        System.out.println();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String keyboardInput = br.readLine();
        os.writeBytes( keyboardInput + "\n" );

        String n =( keyboardInput );
        if ( n.equals("salir") || n.equals("parar") ) {
            break;
        }

        String responseLine = is.readLine();
        System.out.println("la respuesta del servidor es: " + responseLine);
        }

        // limpiar:
        // cerar el output stream
        // cerar el input stream
        // cerar el socket

        os.close();
        is.close();
        clienteSocket.close();   
    } catch (UnknownHostException e) {
        System.err.println("tratando de conectarse a un host deconocido: " + e);
    } catch (IOException e) {
        System.err.println("IOException:  " + e);
    }
    }           
}