package clases;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public Socket socket;
	public ServerSocket serverSocket;
	public final int PUERTO = 1234;
	public DataOutputStream dataOutputStream;
	public String mensaje;
	
	public Servidor() throws IOException {
		this.serverSocket = new ServerSocket(PUERTO); //Inicializamos el servidor
		this.socket = new Socket();
	}
	
	public void runServer() throws IOException {
		System.out.println("Esperando conexiones de algún cliente");
		
		this.socket = this.serverSocket.accept(); //Esperando a que algún cliente se conecte
		
		System.out.println("Cliente conectado");
		
		this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream()); //Flujo donde se guarda lo que se envía al cliente
		this.dataOutputStream.writeUTF("Conexión aceptada");
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		
		while ((this.mensaje = bufferedReader.readLine()) != null) {
			System.out.println(this.mensaje);
		}
		
		this.socket.close();
		this.serverSocket.close();
		
		System.out.println("La conexión con el cliente ha finalizado");
	}
	
}
