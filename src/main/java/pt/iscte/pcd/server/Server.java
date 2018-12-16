package pt.iscte.pcd.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static ServerSocket serverSocket;
	public static final int PORTO = 8500;
	private ArrayList<User> userTable = new ArrayList<User>();

	public static void main(String[] args) {
		try {
			new Server().startServing();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * accepts connection from different clients and launches a Thread for each
	 * one
	 */
	public void startServing() throws IOException {
		serverSocket = new ServerSocket(PORTO);
		System.out.println("Lan�ou ServerSocket: " + serverSocket);
		try {			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Conexao aceite: " + socket);
				new DealWithClient(socket, userTable).start();
			}
		} finally {
			serverSocket.close();
		}
	}

}
