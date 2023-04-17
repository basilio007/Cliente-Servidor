package clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {

	public static void main(String[] args) {
	ServerSocket servidor = null;
	Socket sock = null;
	int PUERTO = 1234;
	DataInputStream in;
	DataOutputStream out;
	
	try {
		servidor = new ServerSocket();
		System.out.println("Servidor inicializado...OK");
		
		
		while(true) {
			sock = servidor.accept();
			System.out.println("cliente conectado...OK");
			in = new DataInputStream(sock.getInputStream());
			out = new DataOutputStream(sock.getOutputStream());
			
			String mensaje = in.readUTF();
			
			System.out.println(mensaje);
			out.writeUTF("hola humano soy el servidor del sistema");
			
			sock.close();
			System.out.println("cliente desconectado...OK");
			
		}
		
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
	}
}
