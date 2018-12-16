package pt.iscte.pcd.server;

import java.net.InetAddress;

public class User {

	private String name;
	private InetAddress address;
	private int port;

	public User(String name, InetAddress address, int port) {
		this.name = name;
		this.address = address;
		this.port = port;
		;
	}

	public String getName() {
		return name;
	}

	public InetAddress getAddress() {
		return address;
	}

	public int getPort() {
		return port;
	}

	public String toString() {
		return (address + " " + port);
	}

}
