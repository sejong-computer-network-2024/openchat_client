package openchat_client;

import java.io.BufferedWriter;
import java.io.IOException;

public class Message {
	private final static int CREAT_ROOM = 1;
	private final static int GET_ROOM_LIST = 2;
	private final static int QUIT_ROOM = 3;
	private final static int CHAT = 4;

	private BufferedWriter writer;
	private String fullMessage;
	private int type;

	public Message(int type, String Message, String RoomId) {
		switch (type) {
		case CREAT_ROOM:
			fullMessage = ""; // 유빈님이 JSON 형태로 fullMessage를 채워주세요.
			break;
		case GET_ROOM_LIST:
			fullMessage = "";
			break;
		case QUIT_ROOM:
			fullMessage = "";
			break;
		case CHAT:
			fullMessage = "";
			break;
		}
	}
	

	public Message(String fullMessage) {
		this.fullMessage = fullMessage;
	}
	
	public int getType() {
		return type;
	}

	public void send() {
		try {
			writer.write(fullMessage);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//메세지 원문을 읽어서 type이랑 그에 해당하는 객체(Chat이나, LinkedList<ChatRoom으로>으로)를 리턴하게 해주세요.
	public Object parse() {
		//
		//type = ? 
		//type에 따른 객체 생성
		return null;
	}
	
}
