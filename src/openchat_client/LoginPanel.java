package openchat_client;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.SocketException;
import java.util.Base64;

import javax.swing.JButton;
import javax.swing.JTextField;

public class LoginPanel extends ClientPanel {
	private BufferedWriter writer;
	private BufferedReader reader;

	private JTextField nameField;
	private JTextField studentIdField;

	private JButton loginBtn;

	public LoginPanel(ClientFrame frame) {
		super(frame);
		writer = frame.getWriter();
		reader = frame.getReader();
		
		setLayout(new GridLayout(3, 1));
		
		loginBtn = new JButton("로그인");
		loginBtn.addMouseListener(new loginBtnListener(frame, nameField, studentIdField, writer));
		
		add(nameField);
		add(studentIdField);
		add(loginBtn);
		

	}


}

class loginBtnListener implements MouseListener {
	private ClientFrame frame;
	private JTextField nameField;
	private JTextField studentIdField;
	private BufferedWriter writer;

	loginBtnListener(ClientFrame frame, JTextField nameField, JTextField studentIdField, BufferedWriter writer) {
		this.frame = frame;
		this.nameField = nameField;
		this.studentIdField = studentIdField;
		this.writer = writer;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(handshake(nameField.getText(), studentIdField.getText()));
			frame.changePanel(frame.ChatListPanel);
	}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}
	
	private boolean handshake(String name, String studentId) {
		try {
			String randomKey = studentId; //랜덤 넘버로 바꾸기 필요
			String webSocketKey = Base64.getEncoder().encodeToString(randomKey.getBytes());
			writer.write("/chat?userId=" + name + "&studentId=" + studentId + " HTTP/1.1");
			writer.write("Host: " + ClientFrame.SERVER_IP + "\r\n");
			writer.write("Upgrade: websocket\r\n");
			writer.write("Connection: Upgrade\r\n");
			writer.write("Sec-WebSocket-Key: " + webSocketKey + "\r\n");
			writer.write("Sec-WebSocket-Version: 13\r\n");
			writer.write("\r\n");
			writer.flush();

		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("handshake 실패 : SocketException");
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("handshake 실패 : IOException");
			return false;
		}
		return true;
	}
}