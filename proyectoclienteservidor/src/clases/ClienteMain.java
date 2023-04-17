package clases;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteMain {

	public static void main(String[] args){
	
		final String HOST = "localhost";
		final int PUERTO = 1234;
		DataInputStream in;
		DataOutputStream out;
		
		try {
			Socket  soc = new Socket(HOST, PUERTO );
			
			in = new DataInputStream(soc.getInputStream());
			out = new DataOutputStream(soc.getOutputStream());
			
			out.writeUTF("fkdjfjfkfdk");
			
			String mensaje = in.readUTF();
			
			System.out.println(mensaje);
			
			soc.close();
			
			
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
