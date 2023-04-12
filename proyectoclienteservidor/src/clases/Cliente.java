package clases;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public Socket socket;
	public DataOutputStream dataOutputStream;
	public final int PUERTO = 1234;
	public final String HOST = "localhost";

	public Cliente() throws UnknownHostException, IOException {
		this.socket = new Socket(HOST, PUERTO);
	}

	public void runCliente() throws IOException {
		

		DataInputStream dataInputStream = new DataInputStream(this.socket.getInputStream());
		System.out.println(dataInputStream.readUTF());
		
		this.dataOutputStream = new DataOutputStream(this.socket.getOutputStream());
		for (int i = 0; i < 5; i++) {
			this.dataOutputStream.writeUTF("Este es el mensaje: " + (i + 1) + " \n");
		}
		
		this.socket.close();
	}
}
