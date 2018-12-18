package pt.iscte.pcd.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class DealWithClient extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ArrayList<User> userTable;
	private User newEntry;

	public DealWithClient(Socket socket, ArrayList<User> userTable) {
		super();
		this.socket = socket;
		this.userTable = userTable;
	}

	@Override
	public void run() {
		try {
			doConnections(socket);
			serve();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * opens both the input and output stream between the server
	 */
	private void doConnections(Socket socket) throws IOException {
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}

	/**
	 * receives input from the Client, deals with it accordingly
	 */
	private void serve() throws IOException {
		while (true) {

			String str = in.readLine();
			 if(str == null){
				 in.close();
				 return;
			 }
				 
			if (str.startsWith("INSC")) {
				String info[] = str.split(" ");
				String textAddress = info[1];
				InetAddress address = InetAddress.getByName(textAddress);
				int port = Integer.parseInt(info[2]);
				newEntry = new User(address, port); // creates user
				addUser(newEntry);
				System.out.println("New entry");
			}

			if (str.equals("CLT")) {
				for (User u : userTable) {
					out.println(u.toString());
					System.out.println(u.toString());
				}
				out.println("END");
			}

			if (str.equals("END")) {
				userTable.remove(newEntry);
				System.out.println("User removed");
				break;
			}
		}
	}
	
	/**
	 * adds a User to the directory
	 */
	public synchronized void addUser(User u) {  //o que esta mal ? passo a classe Server ?
		userTable.add(u); //zona critica
	}

}
