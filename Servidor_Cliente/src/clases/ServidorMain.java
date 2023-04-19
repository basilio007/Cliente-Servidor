package clases;

import java.io.*;
import java.net.*;

public class ServidorMain {
    public static void main(String args[]) {
    int PUERTO = 1234;
    ServidorMain server = new ServidorMain(PUERTO );
    server.startServer();
    }

    // declarando un socket de servidor

    ServerSocket echoServer = null;
    Socket clientSocket = null;
    int port;

    public ServidorMain( int port ) {
    this.port = port;
    }

    public void stopServer() {
    System.out.println( "limpieza del servidor...OK." );
    System.exit(0);
    }

    public void startServer() {
    // para abrir el socket del servidor en el puerto dado   

        try {
        echoServer = new ServerSocket(port);
        }
        catch (IOException e) {
        System.out.println(e);
        }   

    System.out.println( "Esperando conección...OK" );

    // creando un objeto socket desde el ServerSocket para escucha y aceptar la coneccion.
    // Usando ConnecionServidor para procesar la coneccion.

    while ( true ) {
        try {
        clientSocket = echoServer.accept();
        ConnecionServidor oneconnection = new ConnecionServidor(clientSocket, this);
        oneconnection.run();
        }   
        catch (IOException e) {
        System.out.println(e);
        }
    }
    }
}

class ConnecionServidor {
    BufferedReader is;
    PrintStream os;
    Socket clientSocket;
    ServidorMain server;

    public ConnecionServidor(Socket clientSocket, ServidorMain server) {
    this.clientSocket = clientSocket;
    this.server = server;
    System.out.println( "Conección establecida...OK: ");
    try {
        is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        os = new PrintStream(clientSocket.getOutputStream());
    } catch (IOException e) {
        System.out.println(e);
    }
    }

    public void run() {
        String line;
    try {
        boolean serverStop = false;

            while (true) {
                line = is.readLine();
        System.out.println( "el mensaje recibido es: " + line );
        String n = (line);
        if ( n.equals("salir")) {
            serverStop = true;
            break;
        }
        if ( n.equals("parar")) break;
                os.println("" + n ); 
            }

        System.out.println( "Conección terminada...OK...." );
            is.close();
            os.close();
            clientSocket.close();

        if ( serverStop ) server.stopServer();
    } catch (IOException e) {
        System.out.println(e);
    }
    }
}