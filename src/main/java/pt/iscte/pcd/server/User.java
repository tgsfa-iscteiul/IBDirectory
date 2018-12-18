package pt.iscte.pcd.server;

import java.net.InetAddress;

public class User {

	private InetAddress address;
	private int port;

	public User( InetAddress address, int port) {
		this.address = address;
		this.port = port;
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
