package clases;

import java.io.IOException;

public class clase_servidor_cliente {

	public static void main(String[] args) throws IOException {
		Servidor servidor = new Servidor();
		System.out.println("Servidor iniciado");
		servidor.runServer();
	}

}
