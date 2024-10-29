package openchat_client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientFrame extends JFrame {

	public static final int PORT = 6789;
	public static final String SERVER_IP = "localhost";

	private BufferedReader reader;
	private BufferedWriter writer;
	public ClientPanel currentPanel = null;
	public final ClientPanel loginPanel = new LoginPanel(this);
	public final ClientPanel ChatListPanel = new ChatListPanel(this);
	
	private Socket clientSocket;
	
	public ClientFrame(int xSize, int ySize) {
		super();
		setSize(xSize, ySize);
		changePanel(loginPanel);
		socketInit();
	}
	
	public BufferedReader getReader() {
		return reader;
	}
	public BufferedWriter getWriter() {
		return writer;
	}

	private void socketInit() {
		try {
			System.out.println("Socket 초기화");
			clientSocket = new Socket(SERVER_IP, PORT);
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
			writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("Socket 초기화 실패 : SocketException");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Socket 초기화 실패 : IOException");
		}
	}

	public void changePanel(ClientPanel nextPanel) {
		if(currentPanel != null)
			remove(currentPanel);
		currentPanel = nextPanel;
		add(nextPanel);
	}
}
